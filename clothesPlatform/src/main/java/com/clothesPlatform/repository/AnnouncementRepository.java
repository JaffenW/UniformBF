package com.clothesPlatform.repository;

import com.clothesPlatform.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {
    @Query(value = "select * from announcement a where a.title like?1 and a.date between DATE_FORMAT(?2,'%Y-%m-%d') and DATE_FORMAT(?3,'%Y-%m-%d')",nativeQuery = true)
    List<Announcement> findAnnoucementByDate(String title,@DateTimeFormat(pattern = "yyyy-MM-dd") Date start,@DateTimeFormat(pattern = "yyyy-MM-dd") Date end);
    @Query
    List<Announcement> findAllByOrderByDateDesc();
}
