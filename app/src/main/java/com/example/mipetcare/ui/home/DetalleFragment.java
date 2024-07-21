package com.example.mipetcare.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mipetcare.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleFragment extends Fragment {

    private static final int PARAM1=1;
    private static final int PARAM2=2;
    private static final int PARAM3=3;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    static TextView txtnombre;

    static TextView txtdescripcion;

    public DetalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleFragment newInstance(String param1, String param2) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detalle, container, false);
        View view= inflater.inflate(R.layout.fragment_detalle, container, false);
        txtnombre= view.findViewById(R.id.txtnombre);
        txtdescripcion= view.findViewById(R.id.txtdescripcion);

        return view;
    }

    public static void setEstImage(int type) {
        switch (type) {
            case PARAM1:
                txtnombre.setText("squirtle");
                txtdescripcion.setText("Su forma redondeada y las hendiduras que tiene le ayudan a reducir su resistencia al agua y le permiten nadar a gran velocidad. El caparazón de SQUIRTLE no sólo lo protege; su forma " +
                        "redondeada y sus hendiduras reducen su resistencia al agua y le permiten nadar más rápido.");
                break;
            case PARAM2:
                txtnombre.setText("Pikachu");
                txtdescripcion.setText("Pikachu es un personaje perteneciente a la franquicia Pokémon, " +
                        "  \u200B que hizo su primera aparición en los videojuegos Pokémon Rojo y Azul, siendo el Pokémon número 25 de la lista de Pokémon registrada en el Pokédex ");
                break;
            case PARAM3:
                txtnombre.setText("Charizart");
                txtdescripcion.setText("Charizard es una de las criaturas de la franquicia Pokémon. Se trata de un pokémon tipo fuego/volador, que aparece por primera vez en Pokémon Red y Blue, donde puede ser obtenido si el jugador elige ");
                break;

        }



    }


}