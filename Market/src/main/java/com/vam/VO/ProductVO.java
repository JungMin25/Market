package com.vam.VO;

import java.sql.Date;

public class ProductVO {
	private String title,img,content,address,writer;

	private int productNo,categori,price,viewcount,chatcount,likecount;
	
	private Date regDate;
	
	private MemberVO mvo;
	
<<<<<<< HEAD
	private ChattingVO cvo;
	
=======
	private ChatVO cvo;
<<<<<<< HEAD
	private List<ProductImageVO> img;
>>>>>>> 7219e16909f559683a992068b9cd5edb568a4bd9
=======
	
>>>>>>> parent of 7219e16 (글 등록90퍼 완성)

	public ChattingVO getCvo() {
		return cvo;
	}

	public void setCvo(ChattingVO cvo) {
		this.cvo = cvo;
	}

	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getCategori() {
		return categori;
	}

	public void setCategori(int categori) {
		this.categori = categori;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public int getChatcount() {
		return chatcount;
	}

	public void setChatcount(int chatcount) {
		this.chatcount = chatcount;
	}

	public int getLikecount() {
		return likecount;
	}

	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "ProductVO [title=" + title + ", img=" + img + ", content=" + content + ", address=" + address
				+ ", writer=" + writer + ", productNo=" + productNo + ", categori=" + categori + ", price=" + price
				+ ", viewcount=" + viewcount + ", chatcount=" + chatcount + ", likecount=" + likecount + ", regDate="
				+ regDate + ", mvo=" + mvo + ", cvo=" + cvo + "]";
	}
	
	
}
