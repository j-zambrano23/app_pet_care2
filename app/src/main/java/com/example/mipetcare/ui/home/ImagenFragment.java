package com.example.mipetcare.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mipetcare.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImagenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImagenFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PARAM1=1;
    private static final int PARAM2=2;
    private static final int PARAM3=3;
    private OnFragmentInteractionListener listener;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView imgView1;
    ImageView imgView2;
    ImageView imageView3;
    public ImagenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImagenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImagenFragment newInstance(String param1, String param2) {
        ImagenFragment fragment = new ImagenFragment();
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
        // return inflater.inflate(R.layout.fragment_imagen, container, false);
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_imagen, container, false);
        imgView1 = view.findViewById(R.id.imagen1);
        imgView2=  view.findViewById(R.id.imagen2);
        imageView3 =  view.findViewById(R.id.imagen3 );

        imgView1.setOnClickListener(  v-> {
            listener.onHomeSelected(PARAM1);

        });
        imgView2.setOnClickListener(  v -> {
            listener.onHomeSelected(PARAM2);

        });
        imageView3.setOnClickListener(  v-> {
            listener.onHomeSelected(PARAM3);

        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    +"must implement OnFragmentInteractionListener");
        }
    }


    public interface OnFragmentInteractionListener{
        void onHomeSelected(int type);

    }
}