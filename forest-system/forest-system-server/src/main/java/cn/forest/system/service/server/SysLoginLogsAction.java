package cn.forest.system.service.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.forest.common.service.utils.ResultPage;
import cn.forest.system.entity.SysLoginLogs;
import cn.forest.system.mapper.SysLoginLogsMapper;

@RestController
@RequestMapping("sys_login_logs")
public class SysLoginLogsAction {

  @Autowired
  private SysLoginLogsMapper sysLoginLogsMapper;

  @RequestMapping("/add")
  public int add(@RequestBody SysLoginLogs loginLogs) {
    return sysLoginLogsMapper.insert(loginLogs);
  }

  @RequestMapping("/list")
  public Object getList(Long page, Long pageSize) {
    Page<SysLoginLogs> pages = new Page<SysLoginLogs>(page, pageSize);
    IPage<SysLoginLogs> selectPage = sysLoginLogsMapper.selectPage(pages, null);
    return new ResultPage<SysLoginLogs>(selectPage);
  }

}
