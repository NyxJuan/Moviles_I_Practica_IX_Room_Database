package com.example.practica_roomdata.repositorio;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.practica_roomdata.roomdatabase.Animal;
import com.example.practica_roomdata.roomdatabase.AnimalDao;
import com.example.practica_roomdata.roomdatabase.AnimalRoomDatabase;

import java.util.List;

public class AnimalRepositorio {

    private AnimalDao mAnimalDao;
    private LiveData<List<Animal>> mAllAnimales;

    public AnimalRepositorio(Application application){
        AnimalRoomDatabase animalRoomDatabase = AnimalRoomDatabase.getDatabase(application);
        mAnimalDao = animalRoomDatabase.animalDao();
        mAllAnimales = mAnimalDao.getAllAnimales();
    }

    public LiveData<List<Animal>> getAllAnimales(){
        return mAllAnimales;
    }

    public void insert(Animal animal){
        new insertAsyntask(mAnimalDao).execute(animal);
    }

    private static class insertAsyntask extends AsyncTask<Animal,Void,Void> {

        private AnimalDao mAnimalTaskDao;

        insertAsyntask(AnimalDao animalDao){
            mAnimalTaskDao = animalDao;
        }

        @Override
        protected Void doInBackground(Animal... animals) {
            mAnimalTaskDao.insert(animals[0]);
            return null;
        }
    }
}
