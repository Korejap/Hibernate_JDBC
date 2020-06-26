package dao;

public interface PetQuerries {

    public static final String CREATE_TABLE =
            "create table if not exists `student`  (\n" +
                    "`id` integer not null primary key auto_increment,\n" +
                    "`name` varchar(255),\n" +
                    "`ownerName` varchar(255),\n" +
                    "`age` integer,\n" +
                    "`weight` double,\n" +
                    "`pureRace` double,\n" +
                    "`race` varchar(25));"
            ;

    public static final String INSERT_PET =
            "INSERT INTO `pet`\n" +
                    "(`name`, `ownerName`, `age`, `weight`, `pureRace`, `race`) VALUES (?, ?, ?, ?, ?, ?);";

    public static final String SELECT_PETS =
            "SELECT * from `pet`;";

    public static final String DELETE_PET =
            "DELETE FROM `pet` WHERE `id`=?;";



    // TODO: dopisać metodę update.
    //  użytkownik wpisuje identyfikator obiektu i jego dane
    //  nasatępnie program tworzy zapytanie modyfikujące treść obiektu:
}

