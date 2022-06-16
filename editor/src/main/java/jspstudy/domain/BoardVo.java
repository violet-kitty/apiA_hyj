package jspstudy.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//값을 담는 객체 Vo (value object)
public class BoardVo {
	private int bidx;
	private String subject;
	private String content;
	private String writer;
	private String writeday;
	private String delyn;
	private String ip;
	private int midx;
	private int originbidx;
	private int depth;
	private int level_;
	private String filename;	// 업로드한 파일 이름
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getBidx() {
		return bidx;
	}
	public void setBidx(int bidx) {
		this.bidx = bidx;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		
		//날짜 포맷을 yyyy-MM-dd로 변경
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm");
		try {
			Date formatData = date.parse(writeday);
			writeday = formatDate.format(formatData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//포맷 변경된 날짜 값 대입
		this.writeday = writeday;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public int getOriginbidx() {
		return originbidx;
	}
	public void setOriginbidx(int originbidx) {
		this.originbidx = originbidx;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getLevel_() {
		return level_;
	}
	public void setLevel_(int level_) {
		this.level_ = level_;
	}
	
	
}
