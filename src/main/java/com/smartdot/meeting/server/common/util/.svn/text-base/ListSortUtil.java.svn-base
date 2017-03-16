package com.smartdot.meeting.server.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.smartdot.meeting.server.common.entity.Department;

public class ListSortUtil {
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	团集合使用比较器排序
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月7日 下午2:42:19
	 * </pre>
	 * </p>
	 */
	public static void deptTreeSort(List<Department> deptList){
		Collections.sort(deptList, new Comparator<Department>(){
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getGroupnumber().length() > o2.getGroupnumber().length()?1:(  o1.getGroupnumber().length()== o2.getGroupnumber().length()?0:-1);
			} 
		});
		Collections.sort(deptList, new Comparator<Department>(){
			@Override
			public int compare(Department o1, Department o2) {
				return  new Long(o1.getGroupnumber().toString())> new Long(o2.getGroupnumber().toString())?1:(  new Long(o1.getGroupnumber().toString())== new Long(o2.getGroupnumber().toString())?0:-1);
			} 
		});
	}
	public static void main(String[] args) {
		List<Department> deptList = new ArrayList<Department>();
		Department department1 = new Department();
		department1.setGroupnumber("001");
		deptList.add(department1);
		Department department2 = new Department();
		department2.setGroupnumber("001001");
		deptList.add(department2);
		Department department3 = new Department();
		department3.setGroupnumber("003");
		deptList.add(department3);
		Department department4 = new Department();
		department4.setGroupnumber("002");
		deptList.add(department4);
		Department department5 = new Department();
		department5.setGroupnumber("001003");
		deptList.add(department5);
		Department department6 = new Department();
		department6.setGroupnumber("001002");
		deptList.add(department6);
		
		for (int i = 0; i < deptList.size(); i++) {
			System.out.println(deptList.get(i).getGroupnumber());
		}
		deptTreeSort(deptList);
		System.out.println("排序之后");
		for (int i = 0; i < deptList.size(); i++) {
			System.out.println(deptList.get(i).getGroupnumber());
		}
	}
}
