package com.melisasan2017556451.filmuygulamasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

//This fragment is annotated with @AndroidEntryPoint which means that hilt should provide all the dependencies to this fragment that it asks for.
@AndroidEntryPoint
public class FragmentFourth extends Fragment {
    private ListView listView;
    private ArrayList<String> optionsarrayList;
    private ArrayAdapter<String> optionsarrayAdapter;
    private Toolbar toolbar4;
    FirebaseAuth firebaseAuth;
    


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fourth_layout,container,false);

        //Initialization of the Toolbar.
        toolbar4 = rootView.findViewById(R.id.toolbar4);
        toolbar4.setTitle("");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar4);


        firebaseAuth = FirebaseAuth.getInstance();
        listView = rootView.findViewById(R.id.listView);
        optionsarrayList = new ArrayList<>();
        optionsarrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row,optionsarrayList);
        listView.setAdapter(optionsarrayAdapter);

        optionsarrayList.add("My Account");
        optionsarrayList.add("Log Out");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                switch(optionsarrayList.get(i).toString()){
                    case "My Account":

                        //Safe Args generates a FragmentFourthDirections class with two method,
                        // actionFragmentFourthToProfile() that returns a NavDirections object.
                        // This returned NavDirections object can then be passed directly to navigate()
                        NavDirections action =
                                FragmentFourthDirections.actionFragmentFourthToProfile();
                        Navigation.findNavController(view).navigate(action);
                        break;
                    case "Log Out":
                        AlertDialog.Builder logoutAlert = new AlertDialog.Builder(getActivity());
                        logoutAlert.setTitle("Log Out");
                        logoutAlert.setMessage("Do you really want to log out?");
                        logoutAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                firebaseAuth.signOut();
                                Intent intent = new Intent(getActivity(),Login.class);
                                startActivity(intent);
                                getActivity().finish();
                            }

                        });
                        logoutAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        logoutAlert.show();
                        break;
                        default:
                            break;

                }
            }
        });
        return rootView;


                }

            }








