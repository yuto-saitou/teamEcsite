package com.internousdev.espresso.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.espresso.dao.UserInfoDAO;
import com.internousdev.espresso.util.CommonUtility;
import com.internousdev.espresso.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

//ユーザーが重複=エラー文を表示して返す、無ければ確認画面に遷移
//姓からメールアドレスまではエラー画面に遷移しても消えないように保持する

public class CreateUserConfirmAction extends ActionSupport implements SessionAware {
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String sex;
	private String email;
	private String userId;
	private String password;

	private List<String> familyNameErrorMessageList;
	private List<String> firstNameErrorMessageList;
	private List<String> familyNameKanaErrorMessageList;
	private List<String> firstNameKanaErrorMessageList;
	private List<String> emailErrorMessageList;
	private List<String> userIdErrorMessageList;
	private List<String> passwordErrorMessageList;
	private String isExistsUserErrorMessage;

	private Map<String, Object> session;

	public String execute() {
		String result = ERROR;

		session.put("familyName", familyName);
		session.put("firstName", firstName);
		session.put("familyNameKana", familyNameKana);
		session.put("firstNameKana", firstNameKana);
		session.put("sex", sex);
		session.put("email", email);
		session.put("userIdForCreateUser", userId);
		session.put("password", password);

		//未入力項目の有無、入力条件をクリアしているかのチェック
		InputChecker inputCheck = new InputChecker();

		familyNameErrorMessageList = inputCheck.doCheck("姓", familyName, 1, 16, true, true, true, false, true, false);
		firstNameErrorMessageList = inputCheck.doCheck("名", firstName, 1, 16, true, true, true, true, true, true);
		familyNameKanaErrorMessageList = inputCheck.doCheck("姓かな", familyNameKana, 1, 16, false, false, true, false,
				false, false);
		firstNameKanaErrorMessageList = inputCheck.doCheck("名かな", firstNameKana, 1, 16, false, false, true, false,
				false, false);
		emailErrorMessageList = inputCheck.doCheckForEmail(email);
		userIdErrorMessageList = inputCheck.doCheck("ユーザーID", userId, 1, 8, true, false, false, true, false, false);
		passwordErrorMessageList = inputCheck.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false);

		if (familyNameErrorMessageList.size() > 0
				|| firstNameErrorMessageList.size() > 0
				|| familyNameKanaErrorMessageList.size() > 0
				|| firstNameKanaErrorMessageList.size() > 0
				|| emailErrorMessageList.size() > 0
				|| userIdErrorMessageList.size() > 0
				|| passwordErrorMessageList.size() > 0) {

			return result;
		}

		UserInfoDAO userInfoDAO = new UserInfoDAO();
		//ユーザーIDがすでに使用されているかをチェック
		if (userInfoDAO.isExistsUserInfo(userId)) {
			isExistsUserErrorMessage = "使用できないユーザーIDです。";
		} else {
			CommonUtility commonUtility = new CommonUtility();
			password = commonUtility.concealPassword(password);

			result = SUCCESS;
		}
		return result;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getFamilyNameErrorMessageList() {
		return familyNameErrorMessageList;
	}

	public void setFamilyNameErrorMessageList(List<String> familyNameErrorMessageList) {
		this.familyNameErrorMessageList = familyNameErrorMessageList;
	}

	public List<String> getFirstNameErrorMessageList() {
		return firstNameErrorMessageList;
	}

	public void setFirstNameErrorMessageList(List<String> firstNameErrorMessageList) {
		this.firstNameErrorMessageList = firstNameErrorMessageList;
	}

	public List<String> getFamilyNameKanaErrorMessageList() {
		return familyNameKanaErrorMessageList;
	}

	public void setFamilyNameKanaErrorMessageList(List<String> familyNameKanaErrorMessageList) {
		this.familyNameKanaErrorMessageList = familyNameKanaErrorMessageList;
	}

	public List<String> getFirstNameKanaErrorMessageList() {
		return firstNameKanaErrorMessageList;
	}

	public void setFirstNameKanaErrorMessageList(List<String> firstNameKanaErrorMessageList) {
		this.firstNameKanaErrorMessageList = firstNameKanaErrorMessageList;
	}

	public List<String> getEmailErrorMessageList() {
		return emailErrorMessageList;
	}

	public void setEmailErrorMessageList(List<String> emailErrorMessageList) {
		this.emailErrorMessageList = emailErrorMessageList;
	}

	public List<String> getUserIdErrorMessageList() {
		return userIdErrorMessageList;
	}

	public void setUserIdErrorMessageList(List<String> userIdErrorMessageList) {
		this.userIdErrorMessageList = userIdErrorMessageList;
	}

	public List<String> getPasswordErrorMessageList() {
		return passwordErrorMessageList;
	}

	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList) {
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

	public String getIsExistsUserErrorMessage() {
		return isExistsUserErrorMessage;
	}

	public void setIsExistsUserErrorMessage(String isExistsUserErrorMessage) {
		this.isExistsUserErrorMessage = isExistsUserErrorMessage;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
