package com.bilalekrem.jdkcompiler;

public class PojoClass
{
    public String publicText;

    private String privateText;

    public int publicInt;

    private int privateInt;

    public String getPublicText()
    {
        return publicText;
    }

    public void setPublicText(String publicText)
    {
        this.publicText = publicText;
    }

    public String getPrivateText()
    {
        return privateText;
    }

    public void setPrivateText(String privateText)
    {
        this.privateText = privateText;
    }

    public int getPublicInt()
    {
        return publicInt;
    }

    public void setPublicInt(int publicInt)
    {
        this.publicInt = publicInt;
    }

    public int getPrivateInt()
    {
        return privateInt;
    }

    public void setPrivateInt(int privateInt)
    {
        this.privateInt = privateInt;
    }
}
