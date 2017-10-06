package com.ufc.jordao.boaviagem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by pereira on 29/09/17.
 */

public class MenuDialogFragment extends DialogFragment {
    private NotificarEscutadorDoDialog escutador;

    public interface NotificarEscutadorDoDialog {
        public void onDialogEditarClick(int posicao);
        public void onDialogNovoGastoClick(int posicao);
        public void onDialogGastosRealizadosClick(int posicao);
        public void onDialogExcluiClick(int posicao);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final CharSequence[] items = {"Editar", "Novo Gasto", "Gastos Realizados", "Remover"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Opções").setItems(items, itemClick);
        escutador = (NotificarEscutadorDoDialog) getActivity();
        return builder.create();
    }

    DialogInterface.OnClickListener itemClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int item) {
            int pos = MenuDialogFragment.this.getArguments().getInt("pos");
            switch (item) {
                case 0:
                    escutador.onDialogEditarClick(pos);
                    break;
                case 1:
                    escutador.onDialogNovoGastoClick(pos);
                    break;
                case 2:
                    escutador.onDialogGastosRealizadosClick(pos);
                    break;
                case 3:
                    escutador.onDialogExcluiClick(pos);
                    break;
            }
        }
    };
}
