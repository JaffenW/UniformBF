package com.clothesPlatform.controller;

import com.clothesPlatform.entity.Clothing;
import com.clothesPlatform.entity.ClothingImg;
import com.clothesPlatform.entity.ShoppingCart;
import com.clothesPlatform.repository.ClothingImgRepository;
import com.clothesPlatform.repository.ClothingRepository;
import com.clothesPlatform.repository.OrderRepository;
import com.clothesPlatform.service.ClothingImgService;
import com.clothesPlatform.service.ClothingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/clothing")
@CrossOrigin
public class ClothingController {
    @Autowired
    private ClothingService clothingService;
    @Autowired
    private ClothingRepository clothingRepository;
    @Autowired
    private ClothingImgService clothingImgService;
    @Autowired
    private ClothingImgRepository clothingImgRepository;
    @Autowired
    private OrderRepository orderRepository;

    @ApiOperation("分页查询所有的服装数据")
    @GetMapping("findAllClothing/{page}/{size}")
    public Page<Clothing> findAllClothing(@PathVariable("page") int page,@PathVariable("size") int size){
        Pageable pageable = PageRequest.of(page-1,size);
        return clothingService.findAll(page, size);
    }

    @ApiOperation("添加一件服装，传入的参数包括服装类型type，服装描述description，租金rent，服装归属belong，添加成功返回200，失败返回error")
    @PostMapping("/addClothing")
    public String addClothing(@RequestBody Clothing clothing){
        System.out.println("服装对象："+clothing.toString());
        if (clothingService.saveClothing(clothing)) {
            System.out.println();
            return "200";//暂且用成功界面代替
        }else {
            return "error";
        }
    }
    @ApiOperation("删除一件服装，传入的参数clothesId")
    @GetMapping("/deleteClothing/{clothesId}")
    public String deleteClothing(@PathVariable("clothesId")Integer clothesId){
        if (clothingService.deleteClothing(clothesId)) {
            return "success";
        }else {
            return "error";
        }
    }

    @ApiOperation("根据服装类型查找衣服，传入的参数为type，返回一个对象数组")
    @GetMapping("/findClothingByType/{type}")
    @ResponseBody
    public List<Clothing> findClothingByType(@PathVariable("type")String type){
        return clothingService.findClothingByType(type);
    }

    @ApiOperation("通过服装编号查询服装，传入参数为clothesId")
    @GetMapping("/findClothing/{clothesId}")
    public Clothing findClothing(@PathVariable("clothesId") int clothesId) {
        return clothingService.findClothing(clothesId);
    }

    @ApiOperation("通过订单编号查询服装，传入参数为orderId")
    @GetMapping("/findClothingByOrderId/{orderId}")
    public Clothing findClothingByOrderId(@PathVariable("orderId") int orderId) {
        int clothesId1 = orderRepository.findClothingByOrderId(orderId);
        return clothingService.findClothing(clothesId1);
    }

    @ApiOperation("查询单个用户的购物车内的服装，传入参数是uId")
    @GetMapping("/findClothesInCartByUserId/{uId}")
    public List<Clothing> findClothesInCartByUserId(@PathVariable("uId") String uId) {
        return clothingService.findClothesInCartByUserId(uId);
    }

    @ApiOperation("查询单个用户的收藏夹的服装，传入参数是uId")
    @GetMapping("/findClothesInCollectionByUserId/{uId}")
    public List<Clothing> findClothesInCollectionByUserId(@PathVariable("uId") String uId) {
        return clothingService.findClothesInCollectionByUserId(uId);
    }

    @ApiOperation("通过服装类型和所属者查询服装，传入参数为type和belong")
    @GetMapping("/searchClothes/{type}/{belong}")
    public List<Clothing> searchClothes(@PathVariable("type") String type,@PathVariable("belong") String belong) {
        return clothingService.findClothingByTypeAndBelong(type, belong);
    }

    @PostMapping("/addClothingImg")//后面的path是要选择图片存储的文件夹，icon/clo_img/clo_intr_img
    public String addClothingImg(@RequestParam("Img") MultipartFile file, HttpServletRequest request) throws Exception {
        String projectPath = System.getProperty("user.dir");//获取当前项目路径
        String savePath=projectPath+"\\src\\main\\resources\\static";
        System.out.println("savePath:"+savePath);
        String path = null;// 文件路径
        double fileSize = file.getSize();
        System.out.println("文件的大小是"+ fileSize);
        byte[] sizebyte=file.getBytes();
        System.out.println("文件的byte大小是"+ sizebyte.toString());
        if (file != null) {// 判断上传的文件是否为空
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:" + fileName);
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {// 项目在容器中实际发布运行的根路径
                    String imgPath = "clothingImg";
                    path = savePath+"\\"+imgPath+"\\"+fileName;//img/文件夹/文件名
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下:"+path);
                    String path1 = imgPath+"\\"+fileName;
                    //写入到数据库中
                    System.out.println("path:"+path);
                    ClothingImg cloImg=new ClothingImg();
                    cloImg.setClothingId(clothingRepository.findMaxClothesId()+1);
                    cloImg.setImgUrl(imgPath+"\\"+fileName);//省略了前半的文件路径，之后读取的时候要加回来 savePath
                    cloImg.setImgType(imgPath);
                    clothingImgService.saveClothingImg(cloImg);
                    System.out.println(cloImg);
                    return path1;
                }

            } else {
                System.out.println("不是我们想要的文件类型,请按要求重新上传(现在只支持GIF/JPG/PNG三种图片类型)");
                return "SORRY，现在只支持GIF/JPG/PNG三种图片类型，请更换类型";
            }
        } else {
            System.out.println("文件类型为空");
            return "文件类型为空/无法获取文件类型";
        }

        return path;
    }

    @ApiOperation("通过服装编号查询图片")
    @GetMapping("/findClothingImg/{clothingId}")
    public String findClothingImg(@PathVariable("clothingId") int clothingId) {
        String url = clothingImgService.findImgByClothingId(clothingId);
        return url;
    }


}
