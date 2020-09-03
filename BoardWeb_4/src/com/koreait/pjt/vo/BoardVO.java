package com.koreait.pjt.vo;

public class BoardVO {
	private int i_board;
	private String title;
	private String ctnt;
	private int hits;
	private int i_user;
	private String nm;
	private String r_dt;
	private String m_dt;
	private int yn_like;
	private int yn_hate;
	private int c_like;					//좋아요 수
	private int c_cmt;					//댓글 수
	private int record_cnt;				//페이지 당 레코드 수(글 수)
	private int eldx;
	private int sldx;
	private String searchText;
	private String searchType;
	private String profile_img;
	
	
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public int getI_board() {
		return i_board;
	}
	public void setI_board(int i_board) {
		this.i_board = i_board;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCtnt() {
		return ctnt;
	}
	public void setCtnt(String ctnt) {
		this.ctnt = ctnt;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}
	public String getM_dt() {
		return m_dt;
	}
	public void setM_dt(String m_dt) {
		this.m_dt = m_dt;
	}
	public int getYn_like() {
		return yn_like;
	}
	public void setYn_like(int yn_like) {
		this.yn_like = yn_like;
	}
	public int getYn_hate() {
		return yn_hate;
	}
	public void setYn_hate(int yn_hate) {
		this.yn_hate = yn_hate;
	}
	
	public int getC_like() {
		return c_like;
	}
	public void setC_like(int c_like) {
		this.c_like = c_like;
	}
	
	public int getC_cmt() {
		return c_cmt;
	}
	public void setC_cmt(int c_cmt) {
		this.c_cmt = c_cmt;
	}
	public int getRecord_cnt() {
		return record_cnt;
	}
	public void setRecord_cnt(int record_cnt) {
		this.record_cnt = record_cnt;
	}
	public int getEldx() {
		return eldx;
	}
	public void setEldx(int eldx) {
		this.eldx = eldx;
	}
	public int getSldx() {
		return sldx;
	}
	public void setSldx(int sldx) {
		this.sldx = sldx;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
}
