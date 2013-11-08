package org.pxcbpmisframework.core.util;

/**
 * 
 * @ClassName: SystemPath
 * @Description: TODO(得到系统路径)
 * @author Mr_Pxc
 * @date 2013-7-24 上午10:08:37
 * @project_name：BPMIS_1
 * @version 1.0
 */
public class SystemPath {

	/**
	 * @Title: getWebPath
	 * @Description: TODO(例如：F:\my_project\BPMIS_1\WebRoot\)
	 */
	public static String getWebPath() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst(
				"WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	/**
	 * @Title: getClassPath
	 * @Description: TODO(例如：F:\my_project\BPMIS_1\WebRoot\WEB-INF\classes\)
	 */
	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}
	/**
	 * @Title: getPorjectPath
	 * @Description: TODO(例如：F:\my_project\BPMIS_1\)
	 */
	public static String getPorjectPath() {
		String nowpath; 
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}
	
	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static void main(String[] args) {

		System.out.println(getClassPath());
		System.out.println(getPorjectPath());
	}
}
