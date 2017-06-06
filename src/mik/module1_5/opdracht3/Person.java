package mik.module1_5.opdracht3;

/** A person with a name and a sex. 
* @author Rosan Prins(10964711) & Laura Keemink(10912797)*/
public class Person
{
    public static final char MALE = 'm';
    public static final char FEMALE = 'f';
    
    private String name;
    private char sex;
    private int birth, death;

    public Person(String name, char sex, int yearOfBirth, int yearOfDeath)
    {
        this.name = name;
        this.sex = Character.toLowerCase(sex);
        birth = yearOfBirth;
        death = yearOfDeath;
    }

    public Person(String name, char sex)
    {
        this(name, sex, 0, 0);
    }

    Person(String name)
    {
        this(name, MALE, 0, 0);
    }

    public String getName()
    {
        return name;
    }

    public char getSex()
    {
        return sex;
    }

    public boolean isMale()
    {
        return sex == MALE;
    }

    public int getAge()
    {
        return birth == 0 || death == 0 ? 0 : death - birth;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        Person other = (Person) obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        } else if (!name.equals(other.name))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        String life;
        if (birth == 0)
        {
            if (death == 0)
            {
                life = "";
            } else
            {
                life = "(?-" + death + ")";
            }
        } else
        {
            if (death == 0)
            {
                life = "(" + birth + "- )";
            } else
            {
                life = "(" + birth + "-" + death + ")";
            }
        }

        return name + " " + sex + " " + life;
    }
}