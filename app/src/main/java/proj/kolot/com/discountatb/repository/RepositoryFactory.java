package proj.kolot.com.discountatb.repository;


import proj.kolot.com.discountatb.App;
import proj.kolot.com.discountatb.repository.db.AppDatabase;
import proj.kolot.com.discountatb.util.AppExecutors;

public class RepositoryFactory {

    public static EditableRepository getLocalRepository(){
        return new LocalRepository(AppDatabase.getDatabase(App.getInstance()).productDao(), new AppExecutors());
    }

    public static Repository getRemoteRepository(){
        return new RemoteRepository();
    }
}
