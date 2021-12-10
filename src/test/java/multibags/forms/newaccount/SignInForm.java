package multibags.forms.newaccount;

import multibags.forms.XPath;

public enum SignInForm implements XPath {
    EMAIL("//*[@id=\"emailAddress\"]"),
    PASSWORD("//*[@id=\"password\"]"),
    REPEAT_PASSWORD("//*[@id=\"passwordAgain\"]"),
    BUTTON_CREATE_NEW_ACCOUNT("//*[@id=\"registrationForm\"]/button");

    private final String xpath;

    SignInForm(String xpath) {
        this. xpath = xpath;
    }

    @Override
    public String getXpath() {
        return xpath;
    }
}