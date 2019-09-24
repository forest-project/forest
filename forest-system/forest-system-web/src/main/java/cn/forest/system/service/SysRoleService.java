package cn.forest.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.forest.common.entity.JsonNode;
import cn.forest.common.util.JsonUtil;
import cn.forest.common.util.ResultMessage;
import cn.forest.system.remote.SysPermissionsRemote;
import cn.forest.system.remote.SysRoleRemote;

@Service("sysRoleService")
public class SysRoleService {
  @Autowired
  private SysRoleRemote sysRoleRemote;

  @Autowired
  private SysPermissionsRemote sysPermissionsRemote;

  public Map<String, Object> getList(Long page, Long pageSize) {
    Object sysRole = sysRoleRemote.getSysRoleList(page, pageSize);
    if (sysRole != null) {
      return ResultMessage.success(sysRole);
    }
    return null;
  }

  public Map<String, Object> getPermissions() {
    List sysPermissionsList = (List) sysPermissionsRemote.getSysPermissionsList();
    List<JsonNode> jsonNodes = new ArrayList<JsonNode>();
    for (Object obj : sysPermissionsList) {
      Map map = (Map) obj;
      if ("1".equals(map.get("treeDepth").toString())) {
        JsonNode jsonNode = new JsonNode(Long.parseLong(map.get("id").toString()),
            Long.parseLong(map.get("parentId").toString()), map.get("name").toString(),Integer.parseInt(map.get("priority").toString()));
        jsonNode.setChildren(jsonNode(sysPermissionsList, jsonNode));
        jsonNodes.add(jsonNode);
      }
    }
    jsonNodes.sort((e,j)-> e.getPriority()-j.getPriority());
    return ResultMessage.success(jsonNodes) ;
  }

  public List<JsonNode> jsonNode(List sysPermissionsList, JsonNode oldobj) {
    List<JsonNode> JsonNodeList = new ArrayList<JsonNode>();
    for (Object obj : sysPermissionsList) {
      Map map = (Map) obj;
      if (map.get("parentId").toString().equals(oldobj.getId()+"")) {
        JsonNode jsonNode = new JsonNode(Long.parseLong(map.get("id").toString()),
            Long.parseLong(map.get("parentId").toString()), map.get("name").toString(),Integer.parseInt(map.get("priority").toString()));
        JsonNodeList.add(jsonNode);
      }
    }
    for (JsonNode jsonNode : JsonNodeList) {
      List<JsonNode> jsonNodeChildren = jsonNode(sysPermissionsList, jsonNode);
      jsonNodeChildren.sort((e,j)-> e.getPriority()-j.getPriority());
      jsonNode.setChildren(jsonNodeChildren);
    }
    return JsonNodeList;
  }

}
