package com.clothesPlatform.controller;

import com.clothesPlatform.entity.Announcement;
import com.clothesPlatform.service.AnnouncementService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/announcement")
@CrossOrigin
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @ApiOperation("添加公告")
    @PostMapping("saveAnnouncement")
    public String saveAnnouncement(@RequestBody Announcement announcement){
        Date date = new Date();
        announcement.setDate(date);
        String result = announcementService.saveAnnouncement(announcement);
        if (result != null) {
            return "success";
        }else {
            return "error";
        }
    }

    @ApiOperation("分页查询所有公告")
    @GetMapping("findAllAnnouncement/{page}/{size}")
    public Page<Announcement> findAllAnnouncement(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return announcementService.findAllAnnouncement(page,size);
    }

    @ApiOperation("通过公告编号查询公告")
    @GetMapping("/findAnnouncement/{announcementId}")
    public Announcement findAnnoucement(@PathVariable("announcementId") int announcementId) {
        return announcementService.findAnnoucement(announcementId);
    }

    @GetMapping("/findAllByOrderByDateDesc")
    public List<Announcement> findAllByOrderByDateDesc() {
        return announcementService.findAllByOrderByDateDesc();
    }

    @ApiOperation("查询某个时间段的公告，传入参数为start,end")
    @GetMapping("/searchAnnouncements/{title}/{start}/{end}")
    public List<Announcement> searchAnnouncements(@PathVariable("title") String title,@PathVariable("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @PathVariable("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) throws ParseException {
        System.out.println(start);
        System.out.println(end);
        System.out.println(title);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(start);
        String endTime = sdf.format(end);
        Date start1 = sdf.parse(startTime);
        Date end1 = sdf.parse(endTime);
        return announcementService.findAnnouncementByDate(title,start1,end1);
    }

    @ApiOperation("通过公告id删除公告，传入参数是announcementId")
    @DeleteMapping("/deleteAnnouncement")
    public void deleteAnnouncement(int announcementId) {
        announcementService.deleteAnnouncement(announcementId);
    }
}
