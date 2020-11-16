package kr.or.ddit.board.model;

public class BoardVo {
	int board_status;
	String board_name;
	
	public BoardVo(){
		
	}
	public BoardVo(int board_status, String board_name) {
		this.board_status = board_status;
		this.board_name = board_name;
	}

	public int getBoard_status() {
		return board_status;
	}
	public void setBoard_status(int board_status) {
		this.board_status = board_status;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	@Override
	public String toString() {
		return "BoardVo [board_status=" + board_status + ", board_name=" + board_name + "]";
	}
}
