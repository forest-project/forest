package cn.forest.system.fallback;

import org.springframework.stereotype.Component;

import cn.forest.system.remote.SysRoleRemote;

@Component
public class SysRoleBack implements SysRoleRemote {

  @Override
  public Object getSysRoleList(Long page, Long pageSize) {
    return null;
  }

}
