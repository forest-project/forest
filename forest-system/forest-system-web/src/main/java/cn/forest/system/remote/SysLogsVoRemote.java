package cn.forest.system.remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.forest.system.fallback.SysLogsVoBack;

@FeignClient(name = "forest-system-server", fallback = SysLogsVoBack.class)
public interface SysLogsVoRemote {

  @RequestMapping("/sys_logs/list")
  public Object getList(@RequestParam(value = "page") Long page,@RequestParam(value = "pageSize") Long pageSize);
  
}
