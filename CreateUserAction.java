package com.internousdev.espresso.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateUserAction extends ActionSupport implements SessionAware {

	private String backFlag;
	private Map<String, Object> session;

	public String execute() {
		if (backFlag == null) {
			//remove=sessionマップから要素を削除
			session.remove("familyName");
			session.remove("firstName");
			session.remove("familyNameKana");
			session.remove("firstNameKana");
			session.remove("sex");
			session.remove("sexList");
			session.remove("email");
			session.remove("userIdForCreateUser");
			session.remove("password");
		}
		//初期表示で設定している性別(男)を取得
		if (!session.containsKey("sex")) {
			session.put("sex", "男性");
		} else {
			session.put("sex", String.valueOf(session.get("sex")));
		}
		//選択肢を生成
		if (!session.containsKey("sexList")) {
			List<String> sexList = new ArrayList<String>();
			sexList.add("男性");
			sexList.add("女性");
			session.put("sexList", sexList);
		}
		return SUCCESS;
	}

	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	public Map<String,Object>getSession(){
		return session;
	}
	public void setSession(Map<String,Object>session) {
		this.session=session;
	}
}
