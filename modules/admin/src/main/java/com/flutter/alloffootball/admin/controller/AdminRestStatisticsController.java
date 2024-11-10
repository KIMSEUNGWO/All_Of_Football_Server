package com.flutter.alloffootball.admin.controller;

import com.flutter.alloffootball.admin.dto.statistics.RequestDateRange;
import com.flutter.alloffootball.admin.dto.statistics.ResponseRegionStatistics;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.common.enums.region.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminRestStatisticsController {

    @GetMapping
    public String statistics() {
        return "admin_main";
    }

    @ResponseBody
    @GetMapping("/statistics/region")
    public ResponseEntity<Response> getRegionStatistics(@ModelAttribute RequestDateRange dateRange) {
        List<ResponseRegionStatistics> result = List.of(
            ResponseRegionStatistics.builder().region(Region.AKITA.getKo()).completedCount(30).canceledCount(5).build(),
            ResponseRegionStatistics.builder().region(Region.AICHI.getKo()).completedCount(10).canceledCount(0).build(),
            ResponseRegionStatistics.builder().region(Region.AOMORI.getKo()).completedCount(100).canceledCount(21).build(),
            ResponseRegionStatistics.builder().region(Region.CHIBA.getKo()).completedCount(23).canceledCount(3).build(),
            ResponseRegionStatistics.builder().region(Region.EHIME.getKo()).completedCount(8).canceledCount(3).build(),
            ResponseRegionStatistics.builder().region(Region.FUKUI.getKo()).completedCount(55).canceledCount(10).build(),
            ResponseRegionStatistics.builder().region(Region.FUKUOKA.getKo()).completedCount(70).canceledCount(19).build(),
            ResponseRegionStatistics.builder().region(Region.FUKUSHIMA.getKo()).completedCount(1).canceledCount(20).build(),
            ResponseRegionStatistics.builder().region(Region.GIFU.getKo()).completedCount(4).canceledCount(5).build(),
            ResponseRegionStatistics.builder().region(Region.GUNMA.getKo()).completedCount(7).canceledCount(9).build(),
            ResponseRegionStatistics.builder().region(Region.HIROSHIMA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.HOKKAIDO.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.HYOGO.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.IBARAKI.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.ISHIKAWA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.IWATE.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.KAGAWA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.KAGOSHIMA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.KANAGAWA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.KOCHI.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.KUMAMOTO.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.KYOTO.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.MIE.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.MIYAGI.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.MIYAZAKI.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.NAGANO.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.NAGASAKI.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.NARA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.NIIGATA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.OITA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.OKAYAMA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.OSAKA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.SAGA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.SAITAMA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.SHIGA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.SHIMANE.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.SHIZUOKA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.TOCHIGI.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.TOKUSHIMA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.TOKYO.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.TOTTORI.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.TOYAMA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.WAKAYAMA.getKo()).completedCount(9).canceledCount(1).build(),
            ResponseRegionStatistics.builder().region(Region.YAMAGATA.getKo()).completedCount(9).canceledCount(1).build()
        );

        return Response.ok(result);
    }
}
