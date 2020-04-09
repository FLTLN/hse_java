class Majicarp implements Pokemon
{
    String name;
  
    Majicarp()
    {      
        this.name = "Majicarp";
    }
      
    public void choose() 
    {
      
        System.out.printf("%s, I choose you!\n", name);
    }
}