package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	新闻
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月12日 下午2:07:48
 * </pre>
 * </p>
 */

@Entity
@Table(name = "T_NEWS")
public class News extends BaseEntity {

	private static final long serialVersionUID = 3022752943329216618L;
	/**
	 * NEWS_TYPE_COMMON 普通新闻</br>
	 * NEWS_TYPE_CAROUSEL 轮播图</br>
	 * NEWS_LOCATION_HEADLINES 头条
	 */
	public static final String NEWS_LOCATION_COMMON="common";
	/**
	 * NEWS_TYPE_COMMON 普通新闻</br>
	 * NEWS_TYPE_CAROUSEL 轮播图</br>
	 * NEWS_LOCATION_HEADLINES 头条
	 */
	public static final String NEWS_LOCATION_CAROUSEL ="carousel";
	/**
	 * NEWS_TYPE_COMMON 普通新闻</br>
	 * NEWS_TYPE_CAROUSEL 轮播图</br>
	 * NEWS_LOCATION_HEADLINES 头条
	 */
	public static final String NEWS_LOCATION_HEADLINES ="headlines";
	/**
	 * 新闻状态</br>
	 * NEWS_STATUS_SAVE 已保存</br>
	 * NEWS_STATUS_PUBLISH 已发布</br>
	 * NEWS_STATUS_WITHDRAW 已撤回
	 */
	public static final String NEWS_STATUS_SAVE="save";
	/**
	 * 新闻状态</br>
	 * NEWS_STATUS_SAVE 已保存</br>
	 * NEWS_STATUS_PUBLISH 已发布</br>
	 * NEWS_STATUS_WITHDRAW 已撤回
	 */
	public static final String NEWS_STATUS_PUBLISH="publish";
	/**
	 * 新闻状态</br>
	 * NEWS_STATUS_SAVE 已保存</br>
	 * NEWS_STATUS_PUBLISH 已发布</br>
	 * NEWS_STATUS_WITHDRAW 已撤回
	 */
	public static final String NEWS_STATUS_WITHDRAW="withdraw";
	/**
	 * 是否置顶 NEWS_IS_TOP_YES  yes</br>
	 *       NEWS_IS_TOP_NO  no
	 */
	public static final String NEWS_IS_TOP_YES = "yes";
	/**
	 * 是否置顶 NEWS_IS_TOP_YES  yes</br>
	 *       NEWS_IS_TOP_NO  no
	 */
	public static final String NEWS_IS_TOP_NO = "no";
	/**
	 * 排序方式  NEWS_SORT_TYPE_CREATE_TIME 创建时间排序</br>
	 * 		 NEWS_SORT_TYPE_UPDATE_TIME 修改时间排序</br>
	 * 		 NEWS_SORT_TYPE_SORT_FILED  排序字段排序
	 */
	public static final String NEWS_SORT_TYPE_CREATE_TIME="createTime";
	/**
	 * 排序方式  NEWS_SORT_TYPE_CREATE_TIME 创建时间排序</br>
	 * 		 NEWS_SORT_TYPE_UPDATE_TIME 修改时间排序</br>
	 * 		 NEWS_SORT_TYPE_SORT_FILED  排序字段排序
	 */
	public static final String NEWS_SORT_TYPE_UPDATE_TIME="updateTime";
	/**
	 * 排序方式  NEWS_SORT_TYPE_CREATE_TIME 创建时间排序</br>
	 * 		 NEWS_SORT_TYPE_UPDATE_TIME 修改时间排序</br>
	 * 		 NEWS_SORT_TYPE_SORT_FILED  排序字段排序
	 */
	public static final String NEWS_SORT_TYPE_SORT_FILED="sortFiled";
	/**
	 * 新闻位置，普通/轮播图，轮播图的时候，可以上传轮播图图片和轮播图的单独排序
	 */
	private String location;
	/**
	 *语言：zh：中文 en：英文 
	 */
	private String language;
	/**
	 * 新闻所属栏目
	 */
	private String newColumn;
	/**
	 * 新闻类别
	 */
	private String category;
	/**
	 * 按照排序字段排序的时候的排序数值
	 */
	private Integer sortNumber;
	/**
	 * 详情地址
	 */
	private String detailsUrl;
	/**
	 * 缩略图
	 */
	private String zoomPic;
	/**
	 * 名称
	 **/
	private String title;
	/**
	 * 简介
	 * */
	private String remark;
	/**
	 * 富文本内容
	 */
	private String contentHtml;
	/**
	 * 轮播图（当类型为轮播图的时候，多加一个字段去上传图片）
	 */
	private String vieverLunBo;
	/**
	 * 信息状态 1,已发布；2，已保存；3，已撤回
	 */
	private String newStatus;
	/**
	 * 排序类型，创建时间、修改时间、排序字段排序
	 */
	private String sortType;
	/**
	 * 轮播图单独排序字段
	 */
	private Integer vieverSort;
	/**
	 * 这条记录的唯一标示
	 */
	private String uniqueCode;
	/**
	 * 是否置顶
	 */
	private String isTop;
	/**
	 * 头条排序
	 */
	private Integer headlinesSort;
	/**
	 * 头条图片
	 */
	private String headlinesPhoto;
	
	@Column(name = "LOCATION")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "LANGUAGE")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Column(name = "CATEGORY")
	public String getCategory() {
		return category;
	}
	@Column(name = "NEW_COLUMN")
	public String getNewColumn() {
		return newColumn;
	}
	public void setNewColumn(String newColumn) {
		this.newColumn = newColumn;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name = "SORT_NUMBER")
	public Integer getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}
	@Column(name = "DETAILS_URL")
	public String getDetailsUrl() {
		return detailsUrl;
	}
	public void setDetailsUrl(String detailsUrl) {
		this.detailsUrl = detailsUrl;
	}
	@Column(name = "ZOOM_PIC")
	public String getZoomPic() {
		return zoomPic;
	}
	public void setZoomPic(String zoomPic) {
		this.zoomPic = zoomPic;
	}
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "REMARK",length = 2000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "CONTENT_HTML",length = 16777216)
	public String getContentHtml() {
		return contentHtml;
	}
	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}
	@Column(name = "VIEVER_LUNBO")
	public String getVieverLunBo() {
		return vieverLunBo;
	}
	public void setVieverLunBo(String vieverLunBo) {
		this.vieverLunBo = vieverLunBo;
	}
	@Column(name = "NEW_STATUS")
	public String getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	@Column(name = "SORT_TYPE")
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	@Column(name = "VIEVER_SORT")
	public Integer getVieverSort() {
		return vieverSort;
	}
	public void setVieverSort(Integer vieverSort) {
		this.vieverSort = vieverSort;
	}
	@Column(name = "UNIQUE_CODE")
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	@Column(name = "IS_TOP")
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	@Column(name = "HEADLINES_SORT")
	public Integer getHeadlinesSort() {
		return headlinesSort;
	}
	public void setHeadlinesSort(Integer headlinesSort) {
		this.headlinesSort = headlinesSort;
	}
	@Column(name = "HEADLINES_PHOTO")
	public String getHeadlinesPhoto() {
		return headlinesPhoto;
	}
	public void setHeadlinesPhoto(String headlinesPhoto) {
		this.headlinesPhoto = headlinesPhoto;
	}
	
}
