package com.clothesPlatform;

import com.clothesPlatform.entity.ShoppingCart;
import com.clothesPlatform.entity.User;
import com.clothesPlatform.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ClothesPlatformApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClothingRepository clothingRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    DataSource dataSource;
    @Test
    public void cha(){
        List<User> users=userRepository.findAll();
        users.forEach(System.out::println);
    }

    @Test
    void contextLoads() {
        User user = new User();
        user.setUserId("10011");
        user.setPassword("11001");
        userRepository.save(user);
    }

    @Test
    public void testDataSource() throws SQLException {
        System.out.println("数据库为----" + dataSource.getClass());
        System.out.println("数据库连接为----" + dataSource.getConnection());
    }

    @Test
    public void findClothingByOrderId() {
        int clothesId = orderRepository.findClothingByOrderId(5);
        System.out.println(clothesId);
    }

    @Test
    public void findCartByUserId() {
        List<ShoppingCart> cart = shoppingCartRepository.findCartByUserId("10002");
        System.out.println(cart);
    }

    @Test
    public void findRentInCartByUserId() {
        int carts = shoppingCartRepository.findRentInCartByUserId("10002");
        System.out.println(carts);
    }

    @Test
    public void findUsernameByUserId() {
        String name = postRepository.findUsernameByUserId("10002");
        System.out.println(name);
    }

    /*@Test
    public void findAnnouncementByDate() throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date end = dateformat.parse("2020-12-30");
        Date start = dateformat.parse("2016-6-19");
        System.out.println(announcementRepository.findAnnoucementByDate(title,start,end));
    }*/
}
