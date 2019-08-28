package com.hinawi.api.domains;

        import javax.persistence.Column;
        import javax.persistence.Embeddable;
        import javax.persistence.Transient;
        import java.io.Serializable;

/**
 * Bilingual string constant representation. Currently only English (ENG) and French (FRE)
 * languages are supported. Attempt to store a language different from above will result
 * in a {@link IllegalStateException}
 */
@Embeddable
public class MultilingualText implements Serializable {
    public static final String XV_TRUE = "true";
    public static final String XA_DEFAULT = "default";
    public static final String XE_TEXT = "text";

    public static final String LANG_CD_ENG = "ENG";
    public static final String LANG_CD_FRE = "FRE";

    public static final String FIELD_ENG = "_english";
    public static final String FIELD_FRE = "_french";

    @Transient
    private String _defaultLanguage;

    @Column(name = "DESCRIPTION")
    private String _english;

    @Column(name = "ARABIC")
    private String _french;

    private void validateLanguage(String languageCode) {
        if (languageCode == null
                || !(LANG_CD_ENG.equalsIgnoreCase(languageCode)
                || LANG_CD_FRE.equalsIgnoreCase(languageCode))) {
            throw new IllegalStateException("Language [" + languageCode + "] not supported");
        }
    }

    public void addText(String language, String text) {
        addText(language, text, false);
    }

    public void addText(String language, String text, boolean isDefault) {
        validateLanguage(language);

        if (language.trim().length() > 0) {
            if (_defaultLanguage == null || isDefault) {
                _defaultLanguage = language.toUpperCase();
            }
            setText(language, text);
        }
    }

    private void setText(String language, String text) {
        if (LANG_CD_ENG.equalsIgnoreCase(language)) {
            _english = text;
        } else {
            _french = text;
        }
    }

    public boolean setDefault(String language) {
        validateLanguage(language);

        if (LANG_CD_ENG.equalsIgnoreCase(language) && _english != null ||
                LANG_CD_FRE.equalsIgnoreCase(language) && _french != null) {

            _defaultLanguage = language.toUpperCase();
            return true;
        }

        return false;
    }

    public String getText(String language, boolean useDefault) {
        validateLanguage(language);

        String retVal = LANG_CD_ENG.equalsIgnoreCase(language) ? _english : _french;

        if (retVal == null && useDefault && _defaultLanguage != null) {
            retVal = getText(_defaultLanguage, false);
        }
        if (retVal == null && useDefault) {
            retVal = "";
        }
        return retVal;
    }

    public String getText(String language) {
        return getText(language, true);
    }

    public String toString() {
        StringBuilder retVal = new StringBuilder();
        retVal.append("\n[MulilingualText]");
        if (_defaultLanguage == null) {
            retVal.append("\nDefault Value not set");
        } else {
            retVal.append("\nDefault Value:").append(_defaultLanguage);
        }
        if (_english == null && _french == null) {
            retVal.append("\nNo Text!");
        } else {
            if (_english != null) {
                retVal.append("\nLanguage:").append(LANG_CD_ENG).append(" = ").append(_english);
            }
            if (_french != null) {
                retVal.append("\nLanguage:").append(LANG_CD_FRE).append(" = ").append(_french);
            }
        }

        return retVal.toString();
    }

    public String getEnglish() {
        return _english;
    }

    public void setEnglish(String _english) {
        this._english = _english;
    }

    public String getFrench() {
        return _french;
    }

    public void setFrench(String _french) {
        this._french = _french;
    }
}