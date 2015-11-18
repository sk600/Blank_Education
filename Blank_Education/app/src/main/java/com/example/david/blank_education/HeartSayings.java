package com.example.david.blank_education;

import cn.bmob.v3.BmobObject;

/**
 * Created by 123 on 2015/11/17.
 */
public class HeartSayings extends BmobObject {

    private String title,author,content;
    private String[] answer;
    private Integer amountOfGood = new Integer(0);
    private Integer amountOfBad = new Integer(0);
    Integer amountOfAnswer = new Integer(0);
    private int id;

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setAnswer(String answer)
    {
        int amount = amountOfAnswer.intValue();
        this.answer[amount] = answer;
        amount++;
        amountOfAnswer = new Integer(amount);
    }

    public String[] getAnswer()
    {
        return answer;
    }

    public void setAmountOfGood()
    {
        int amount = amountOfGood.intValue();
        amount++;
        amountOfGood = new Integer(amount);
    }

    public Integer getAmountOfGood()
    {
        return amountOfGood;
    }

    public void setAmountOfBad()
    {
        int amount = amountOfBad.intValue();
        amount++;
        amountOfBad = new Integer(amount);
    }

    public Integer getAmountOfBad()
    {
        return amountOfBad;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
}
