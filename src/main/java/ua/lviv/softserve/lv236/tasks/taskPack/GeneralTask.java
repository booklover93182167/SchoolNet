package ua.lviv.softserve.lv236.tasks.taskPack;

public abstract class GeneralTask
{
    protected String name = "";
    protected String description = "";
    private String input = "";
    private String result = "";

    public abstract void countResult();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name != null)
            this.name = name.trim();
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        if (description != null)
            this.description = description.trim();
    }

    public String getInput()
    {
        return input;
    }

    public void setInput(String input)
    {
        if (input != null)
            this.input = input.trim();
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        if (result != null)
            this.result = result.trim();
    }
}
