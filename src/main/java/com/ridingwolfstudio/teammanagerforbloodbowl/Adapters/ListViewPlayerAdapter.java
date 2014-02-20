package com.ridingwolfstudio.teammanagerforbloodbowl.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Player;
import com.ridingwolfstudio.teammanagerforbloodbowl.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListViewPlayerAdapter extends ArrayAdapter<Player> {
    private final Context _context;
    private final List<Player> Players;

    public ListViewPlayerAdapter(Context context, List<Player> players) {
        super(context, R.layout.team_playerlist_item, players);
        _context = context;
        this.Players = players;
    }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
       Player currentPlayer = Players.get(position);
       PlayerViewGenerator viewGenerator = new PlayerViewGenerator(parent, currentPlayer);

       return viewGenerator.createView();
   }


    private class PlayerViewGenerator {
        private final View _view;
        private final Player _player;

        public PlayerViewGenerator(ViewGroup parent, Player player){
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            _view = inflater.inflate(R.layout.team_playerlist_item, parent,false);
            _player = player;
        }

        public View createView(){
            if(null == _view)
                return null;

            setTextValue(R.id.playerId, _player.Nr);
            setTextValue(R.id.playerName, _player.Name);
            setTextValue(R.id.playerType, _player.Type);
            setTextValue(R.id.playerMovement, _player.Movement);
            setTextValue(R.id.playerStrength, _player.Strength);
            setTextValue(R.id.playerAgility, _player.Agility);
            setTextValue(R.id.playerArmour, _player.Armour);
            setTextValue(R.id.playerSkills, _player.StartingSkills);

            return _view;
        }

        private void setTextValue(int fieldId, Object[] value){
            if(null == value)
                setTextValue(fieldId, value);
            else{
                String stringValue = "";
                for(int i = 0; i < value.length; i++){
                    if(false == stringValue.trim().isEmpty())
                        stringValue = stringValue + ", ";
                    stringValue = stringValue + value[i];
                }
                setTextValue(fieldId, stringValue);
            }
        }



            private void setTextValue(int fieldId, Object value){
            TextView nameView = (TextView) _view.findViewById(fieldId);
            nameView.setText(null == value? "" : value.toString());
        }

    }
}
