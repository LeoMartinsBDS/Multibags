package multibags.forms.newaccount;

import multibags.forms.XPath;

public enum PersonalInformationForm implements XPath {
    FIRST_NAME("//*[@id=\"firstName\"]"),
    LAST_NAME("//*[@id=\"lastName\"]"),
    COUNTRY("//*[@id=\"registration_country\"]"),
    STATE("//*[@id=\"hidden_zones\"]");

    private final String xpath;

    PersonalInformationForm(String xpath) {
        this. xpath = xpath;
    }

    @Override
    public String getXpath() {
        return xpath;
    }
}