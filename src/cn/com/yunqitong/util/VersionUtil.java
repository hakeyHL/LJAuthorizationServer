package cn.com.yunqitong.util;

import net.sf.json.JSONObject;

/**
 * 用于对每个提交请求的客户端的版本检测控制
*  规则:只支持当前版本,以往版本不兼容。
* 项目名称：AuthorizationServer   
* 类名称：VersionUtil   
* 创建人：huli   
* 创建时间：2015-12-26 上午9:38:55      
 */
public class VersionUtil {
	public static boolean versionCheck(String plateform,String version){
		return true;
	}
	/**
	 * @param build 内部版本
	 * @param version 使用版本
	 * @return 是否可用
	 */
	public static JSONObject standVersionCheck(String version){
		JSONObject v_check_result=new JSONObject();
		if(version.equals("")){
			v_check_result.put("errorcode", "20000");
			v_check_result.put("msg", "version 不能为空");
			return v_check_result;
		}
		if(version==null||version.equals("")){
			v_check_result.put("errorcode", "20010");
			v_check_result.put("msg", "版本过低,请升级");
			return v_check_result;
		}
		//1.服务器版本
		String serverVersion = GetVersion.version;
		//2.将版本转为整数进行比较
		int t_serverVersion=getVersionInt(serverVersion);
		//服务器版本
		int t_version=getVersionInt(version);
		//3.根据客户端传来参数匹配比较
			//如果是安卓
			if(t_version<t_serverVersion){
				//如果客户端版本低于server最小版本,提示升级
				v_check_result.put("errorcode", "20008");
				v_check_result.put("msg", "版本过低,请升级");
				return v_check_result;
			}
			if(t_version>t_serverVersion){
				//如果移动端版本大于了server版本,提示请联系管理员对服务系统升级
				v_check_result.put("errorcode", "20010");
				v_check_result.put("msg", "当前server版本过低,请联系管理员对server升级");
				return v_check_result;
			}
		//4.将结果返回
		//其他情况,全部通过
		v_check_result.put("errorcode", "00000");
		v_check_result.put("msg", "通过");
		return v_check_result;
	}
	/**
	 * 将version/build转为整数
	 * @param version 只取前两位
	 * @return
	 */
	public static Integer getVersionInt(String version){
		String []temp=version.split("\\.");
		int result=0;
		for(int i=0;i<2;i++){
			result=result*10+Integer.valueOf(temp[i]);
		}
		return result;
	}
}
