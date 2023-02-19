package com.jizhi.phonemall.controller;
import	java.util.ArrayList;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jizhi.phonemall.entity.*;
import com.jizhi.phonemall.service.*;
import com.jizhi.phonemall.util.MD5Util;
import com.jizhi.phonemall.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;
    @Autowired
    @Qualifier("goodsService")
    private GoodsService goodsService;
    @Autowired
    @Qualifier("usersService")
    private UsersService usersService;
    @Autowired
    @Qualifier("adminService")
    private AdminService adminService;
    @Autowired
    @Qualifier("recommendService")
    private RecommendService recommendService;
    @Autowired
    @Qualifier("orderItemService")
    private OrderItemService orderItemService;
    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;


    //request.setAttribute("flag",1);
    //request.setAttribute("status",(status+1));
    //flag和status属性设置对应着相关的高亮/按钮显示

    //-----------------------------------------------------------------------
    //管理员操作
    /**
     * 管理员登录
     * @param admin
     * @param request
     * @param session
     * @return
     */
    @PostMapping("login")
    public String login(Admin admin, HttpServletRequest request, HttpSession session){
        if(adminService.checkAdmin(admin.getUsername(), admin.getPassword())){
            session.setAttribute("ADMIN",admin);
            //重定向至显示后台主页方法
            return "redirect:index";
        }else{
            request.setAttribute("message","名称或密码出错了");
            //返回登录页面
            return "/admin/login";
        }
    }
    /**
     * 管理员登出
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session){
        //移出session中的ADMIN属性
        session.removeAttribute("ADMIN");
        return "redirect:toLogin";
    }
    /**
     * 前往登录页面
     *
     * @return
     */
    @GetMapping("toLogin")
    public String toLogin(HttpSession session) {
        //检查session中是否存在管理员信息
        if(session.getAttribute("ADMIN") != null){
            return "redirect:index";
        }
        return "/admin/login";
    }
    /**
     * 前往管理员密码重置页面
     *
     * @return
     */
    @GetMapping("toAdminReset")
    public String toAdminReset(HttpServletRequest request) {
        request.setAttribute("flag",5);
        return "/admin/admin_reset";
    }
    /**
     * 前往登录后的主页面
     *
     * @return
     */
    @GetMapping("index")
    public String toIndex() {
        return "/admin/index";
    }




    /**
     * 订单查询页面
     *
     * @return
     */
    @GetMapping("toOrderList")
    //@RequestParam(required = false,defaultValue = "-1") 该参数并不是必需传入的，默认值为-1
    public String toOrderList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "-1") Integer status) {
        request.setAttribute("flag",1);
        List<Orders> allOrders = null;
        //代表查询全部订单
        if(status == -1){
            request.setAttribute("status",0);
            allOrders = orderService.findAllOrders(null);
        }else{
            request.setAttribute("status",status);
            allOrders = orderService.findAllOrders(status);
        }
        request.setAttribute("orderList",allOrders);
        return "/admin/order_list";
    }
    /**
     * 通过id查询订单
     * @return
     */
    @PostMapping("orderSearch")
    public String orderSearch(HttpServletRequest request,Integer oid) {
        request.setAttribute("flag",1);
        //由于前台是通过<c:for></C:for>,进行读取，所以即使是仅有一个订单，也要将其放到集合中
        List<Orders> allOrders = new ArrayList<> ();
        request.setAttribute("status",0);
        //向集合中添加订单
        allOrders.add(orderService.findOrdersById(oid));
        request.setAttribute("orderList",allOrders);
        return "/admin/order_list";
    }
    /**
     * 设置订单状态为已发货
     * @return
     */
    @GetMapping("orderDispose")
    public String orderDispose(HttpServletRequest request,Integer oid,Integer status) {
        request.setAttribute("flag",1);
        request.setAttribute("status",(status+1));
        //查询所有已发货的订单
        List<Orders> allOrders = orderService.findAllOrders(Orders.STATUS_SEND);
        Orders orders = orderService.findOrdersById(oid);
        //设置订单状态
        orders.setStatus((status + 1));
        //更新订单信息
        orderService.updateInfo(orders);
        //向订单集合中
        allOrders.add(orders);
        request.setAttribute("orderList",allOrders);
        return "/admin/order_list";
    }


    //-----------------------------------
    //分类管理


    /**
     * 查询(查询全部或模糊查询)分类
     * @param request
     * @param cname
     * @return
     */
    @GetMapping("/categoryList")
    public String categoryList(HttpServletRequest request, @RequestParam(required = false) String cname) {
        request.setAttribute("flag", 4);
        List<Category> list = null;
        list = categoryService.findAllCategory(cname);
        request.setAttribute("categoryList", list);
        return "/admin/category_list";
    }
    /**
     * 进行分类添加
     * @param request
     * @return
     */
    @PostMapping("/addCategory")
    public String addCategory(HttpServletRequest request,String cname){
        Category category = new Category();
        category.setCname(cname);
        categoryService.addCategory(category);
        return "redirect:categoryList";
    }




    //商品管理
    /**
     * 修改商品信息
     * @param gname
     * @param images1
     * @param images2
     * @param price
     * @param stock
     * @param intro
     * @param categoryId
     * @return
     */
    @PostMapping("/goodsUpdate")
    public String goodsUpdate(@RequestParam("gname") String gname,Integer gid, MultipartFile images1, MultipartFile images2,
                           String price, String stock, String intro, String categoryId) {
        Goods goods = new Goods();
        goods.setGid(gid);
        goods.setGname(gname);
        goods.setPrice(Double.parseDouble(price));
        goods.setStock(Integer.parseInt(stock));
        goods.setIntro(intro);
        goods.setCategoryId(Integer.parseInt(categoryId));
        //判断对象不为空
        if(Objects.nonNull(images1) && !images1.isEmpty()){
            goods.setImages1(UploadUtil.fileUpload(images1));
        }
        if(Objects.nonNull(images2) && !images2.isEmpty()){
            goods.setImages2(UploadUtil.fileUpload(images2));
        }
        int number = goodsService.editGoods(goods);
        return "redirect:goodsList";
    }
    /**
     * 商品添加
     * @param gname
     * @param images1
     * @param images2
     * @param price
     * @param stock
     * @param intro
     * @param categoryId
     * @return
     */
    @PostMapping("/addGoods")
    public String addGoods(@RequestParam("gname") String gname, MultipartFile images1, MultipartFile images2,
                           String price, String stock, String intro, String categoryId) {
        Goods goods = new Goods();
        goods.setGname(gname);
        goods.setPrice(Double.parseDouble(price));
        goods.setStock(Integer.parseInt(stock));
        goods.setIntro(intro);
        goods.setCategoryId(Integer.parseInt(categoryId));
        goods.setImages1(UploadUtil.fileUpload(images1));
        goods.setImages2(UploadUtil.fileUpload(images2));
        int number = goodsService.addGoods(goods);
        return "redirect:goodsList";
    }
    /**
     * 前往添加商品页面
     *
     * @param request
     * @return
     */
    @GetMapping("toAddGoodsPage")
    public String toAddGoodsPage(HttpServletRequest request) {
        request.setAttribute("flag", 3);
        List<Category> allCategory = categoryService.findAllCategory(null);
        request.setAttribute("cateogrylist", allCategory);
        return "/admin/good_add";
    }
    /**
     * 商品修改 -  数据回显
     * @param request
     * @param gid
     * @return
     */
    @GetMapping("/showGoodsEdit")
    public String showGoodsEdit(HttpServletRequest request,Integer gid){
        request.setAttribute("flag",3);
        Goods goods = goodsService.findGoodsById(gid);
        Category category = categoryService.findCategoryById(goods.getGid());
        List<Category> categoryList = categoryService.findAllCategory(null);
        goods.setCategory(category);
        request.setAttribute("goods",goods);
        request.setAttribute("categoryList",categoryList);
        return "/admin/good_edit";
    }
    /**
     * 商品修改 -  删除
     * @param gid
     * @return
     */
    @GetMapping("/goodsDelete")
    public String goodsDelete(Integer gid){
        goodsService.delGoods(gid);
        //
        recommendService.delRecommend(gid, (byte) 0);
        orderItemService.delByGid(gid);
        return "redirect:goodsList";
    }
    /**
     * 查询（在不同条件下）所有商品
     *
     * @param request
     * @return
     */
    @RequestMapping("/goodsList")
    public String goodsList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                            @RequestParam(required = false,defaultValue = "0") Integer status,@RequestParam(required = false,defaultValue = "-1")String gname) {
        request.setAttribute("flag", 3);
        //设置分页从第几页开始，每页大小
        PageHelper.startPage(pageNum,5);
        List<Goods> allGoods = null;
        if(gname.equals("-1")) {
            allGoods = goodsService.findAllGoods(null, status);
        }else {
            allGoods = goodsService.findGoodsByLikeName(gname);
            request.setAttribute("gnames",gname);
        }
        PageInfo pageInfo = new PageInfo(allGoods);
        request.setAttribute("goodsList", pageInfo);
        request.setAttribute("status", status);
        return "/admin/good_list";
    }


    /**
     * 商品的条幅、热销等状态设置
     * @param goodsId
     * @param type
     * @return
     */
    @PostMapping("operateRecommend")
    @ResponseBody
    public String operateRecommend(Integer goodsId,Byte type){
        int number = -1;
        if(recommendService.isExist(goodsId,type)){
            number = recommendService.delRecommend(goodsId,type);
        }else{
            Recommend recommend = new Recommend();
            recommend.setType(type);
            recommend.setGoodsId(goodsId);
            number = recommendService.addRecommend(recommend);
        }
        if(number > 0){
            return "true";
        }else{
            return "false";
        }
    }


    //------------------------------------
    //用户管理

    /**
     * 显示用户列表
     * @param request
     * @return
     */
    @GetMapping("/userList")
    public String userList(HttpServletRequest request) {
        request.setAttribute("flag", 2);//flag是header.jsp中的一个属性,代表哪一项高亮
        List<Users> allUsers = usersService.findAllUsers(0, 5);
        request.setAttribute("userList", allUsers);
        return "/admin/user_list";
    }
    /**
     * 模糊查询用户
     * @param request
     * @param username
     * @return
     */
    @PostMapping("userSearch")
    public String userSearch(HttpServletRequest request, String username) {
        request.setAttribute("flag", 2);
        List<Users> allUsers = usersService.findUserByLikeName(username);
        request.setAttribute("userList", allUsers);
        return "/admin/user_list";
    }
    /**
     * 前往用户添加页面
     * @param request
     * @return
     */
    @GetMapping("toAddUserPage")
    public String toAddUserPage(HttpServletRequest request) {
        request.setAttribute("flag", 2);
        return "/admin/user_add";
    }
    @PostMapping("userAdd")
    public String userAdd(HttpServletRequest request,Users user) {
        request.setAttribute("flag", 2);
        user.setPassword(MD5Util.encode(user.getPassword()));
        if(usersService.isExist(user.getUsername())){
            request.setAttribute("message","该用户昵称已存在，请更换！");
            return "/admin/user_add";
        }
        int number = usersService.addUser(user);
        request.setAttribute("message", "添加成功");
        return "redirect:userList";
    }

    /**
     * 重置密码，页面跳转
     * @param request
     * @param uid
     * @return
     */
    @GetMapping("resetPassword")
    public String resetPassword(HttpServletRequest request,int uid){
        request.setAttribute("flag", 2);
        Users user = usersService.findUserById(uid);
        request.setAttribute("user",user);
        return "/admin/user_reset";
    }
    /**
     * 用户密码重置ing
     * @param request
     * @param user
     * @return
     */
    @PostMapping("userReset")
    public String userReset(HttpServletRequest request,Users user){
        request.setAttribute("flag", 2);
        Users userById = usersService.findUserById(user.getUid());
        userById.setPassword(MD5Util.encode(user.getPassword()));
        int number = usersService.editUser(userById);
        request.setAttribute("message", "重置成功");
        return "redirect:userList";
    }
    /**
     * 修改用户-数据回显
     * @param request
     * @param user
     * @return
     */
    @GetMapping("toEditUserPage")
    public String toEditUserPage(HttpServletRequest request,Users user){
        request.setAttribute("flag", 2);
        user = usersService.findUserById(user.getUid());
        request.setAttribute("user",user);
        return "/admin/user_edit";
    }

    /**
     * 编辑用户
     * @param request
     * @param user
     * @return
     */
    @PostMapping("editUser")
    public String editUser(HttpServletRequest request,Users user){
        request.setAttribute("flag", 2);
        request.setAttribute("message", "编辑成功");
        int number = usersService.editUser(user);
        return "redirect:userList";
    }

    /**
     * 删除用户
     * @param request
     * @param uid
     * @return
     */
    @GetMapping("deleteUser")
    public String deleteUser(HttpServletRequest request,Integer uid){
        request.setAttribute("flag", 2);
        int number = usersService.delUser(uid);
        request.setAttribute("message", "删除成功");
        return "redirect:userList";
    }
}
