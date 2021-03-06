package cn.forest.common.token;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
  static final long EXPIRATIONTIME = 600000; // 5天
  static final String SECRET = "278U@#$%^ki0op23lwe"; // JWT密码
  static final String HEADER_STRING = "torestToken";// 存放Token的Header Key

  // JWT生成方法
  public static String addAuthentication(String username) throws JSONException {
    // 生成JWT
    String jwt = Jwts.builder()
        // 保存权限（角色）
        .setSubject(username)
        // 有效期设置
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
        // 签名设置
        .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    return jwt;

  }

  public static String getAuthenticationUser(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);

    if (token != null) {
      Claims claims = claims = Jwts.parser()
          // 验签
          .setSigningKey(SECRET)
          // 去掉 Bearer
          .parseClaimsJws(token).getBody();
      // 拿用户名
      String user = claims.getSubject();
      return user;
    }
    return null;
  }
}
