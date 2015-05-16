package com.alvardev.demos.shopmedical.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.R;

public class PedidoDialogFragment extends DialogFragment {

    private int cantidad;
    private int position;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        position = getArguments().getInt("position");
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Pedido")
                .setTitle("Ingrese una cantidad");


        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.layout_cant_picker, null);
        builder.setView(view);

        NumberPicker np = (NumberPicker)view.findViewById(R.id.numberPicker);
        String[] nums = new String[20];
        for(int i=0; i<nums.length; i++)
            nums[i] = Integer.toString(i);

        np.setMinValue(0);
        np.setMaxValue(19);
        np.setWrapSelectorWheel(false);
        np.setDisplayedValues(nums);
        np.setValue(0);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setOnValueChangedListener(( new NumberPicker.
                OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                cantidad = newVal;
            }
        }));

        // Add the buttons
        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                //if(cantidad>0){
                    mListener.onDialogPositiveClick(PedidoDialogFragment.this,cantidad, position);
                //}else{
                //    Toast.makeText(getActivity().getApplicationContext(), "Ingrese una cantidad mayor a 0", Toast.LENGTH_SHORT).show();
                //}
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                mListener.onDialogNegativeClick(PedidoDialogFragment.this);
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        return builder.create();
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface PedidoDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, int cantidad, int position);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    private PedidoDialogListener mListener;


    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (PedidoDialogListener) activity;

        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement PedidoDialogListener");
        }
    }






}
