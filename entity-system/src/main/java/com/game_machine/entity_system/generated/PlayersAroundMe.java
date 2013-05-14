// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from messages.proto

package com.game_machine.entity_system.generated;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import com.dyuproject.protostuff.GraphIOUtil;
import com.dyuproject.protostuff.Input;
import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.Output;

import java.io.ByteArrayOutputStream;
import com.dyuproject.protostuff.JsonIOUtil;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.game_machine.entity_system.Entity;
import com.game_machine.entity_system.Entities;
import com.game_machine.entity_system.Component;

import com.dyuproject.protostuff.Pipe;
import com.dyuproject.protostuff.Schema;

public final class PlayersAroundMe extends com.game_machine.entity_system.Component implements Externalizable, Message<PlayersAroundMe>, Schema<PlayersAroundMe>
{

    public static Schema<PlayersAroundMe> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static PlayersAroundMe getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final PlayersAroundMe DEFAULT_INSTANCE = new PlayersAroundMe();

    
    private List<Player> player;
    private Integer entityId;

    public PlayersAroundMe()
    {
        
    }

    // getters and setters

    // player

    public List<Player> getPlayerList()
    {
        return player;
    }

    public void setPlayerList(List<Player> player)
    {
        this.player = player;
    }

    public Player getPlayer(int index)
    {
        return player == null ? null : player.get(index);
    }

    public int getPlayerCount()
    {
        return player == null ? 0 : player.size();
    }

    public void addPlayer(Player player)
    {
        if(this.player == null)
            this.player = new ArrayList<Player>();
        this.player.add(player);
    }

    // entityId

    public Integer getEntityId()
    {
        return entityId;
    }

    public void setEntityId(Integer entityId)
    {
        this.entityId = entityId;
    }

    // java serialization

    public void readExternal(ObjectInput in) throws IOException
    {
        GraphIOUtil.mergeDelimitedFrom(in, this, this);
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        GraphIOUtil.writeDelimitedTo(out, this, this);
    }

    // message method

    public Schema<PlayersAroundMe> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public PlayersAroundMe newMessage()
    {
        return new PlayersAroundMe();
    }

    public Class<PlayersAroundMe> typeClass()
    {
        return PlayersAroundMe.class;
    }

    public String messageName()
    {
        return PlayersAroundMe.class.getSimpleName();
    }

    public String messageFullName()
    {
        return PlayersAroundMe.class.getName();
    }

    public boolean isInitialized(PlayersAroundMe message)
    {
        return true;
    }

    public void mergeFrom(Input input, PlayersAroundMe message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    if(message.player == null)
                        message.player = new ArrayList<Player>();
                    message.player.add(input.mergeObject(null, Player.getSchema()));
                    break;

                case 2:
                    message.entityId = input.readInt32();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, PlayersAroundMe message) throws IOException
    {
        if(message.player != null)
        {
            for(Player player : message.player)
            {
                if(player != null)
                    output.writeObject(1, player, Player.getSchema(), true);
            }
        }


        if(message.entityId != null)
            output.writeInt32(2, message.entityId, false);
    }

    public String getFieldName(int number)
    {
        switch(number)
        {
            case 1: return "player";
            case 2: return "entityId";
            default: return null;
        }
    }

    public int getFieldNumber(String name)
    {
        final Integer number = __fieldMap.get(name);
        return number == null ? 0 : number.intValue();
    }

    private static final java.util.HashMap<String,Integer> __fieldMap = new java.util.HashMap<String,Integer>();
    static
    {
        __fieldMap.put("player", 1);
        __fieldMap.put("entityId", 2);
    }
    
    static final Pipe.Schema<PlayersAroundMe> PIPE_SCHEMA = new Pipe.Schema<PlayersAroundMe>(DEFAULT_INSTANCE)
    {
        protected void transfer(Pipe pipe, Input input, Output output) throws IOException
        {
            for(int number = input.readFieldNumber(wrappedSchema);; number = input.readFieldNumber(wrappedSchema))
            {
                switch(number)
                {
                    case 0:
                        return;
                    case 1:
                        output.writeObject(number, pipe, Player.getPipeSchema(), true);
                        break;

                    case 2:
                        output.writeInt32(number, input.readInt32(), false);
                        break;
                    default:
                        input.handleUnknownField(number, wrappedSchema);
                }
            }
        }
    };

    public static Pipe.Schema<PlayersAroundMe> getPipeSchema()
    {
        return PIPE_SCHEMA;
    }

    //__REPLACE_1_START__
    public Entities toEntities() {
      return null;
    }
    public static Components fromEntities(Entities entities) {
      return null;
    }
    //__REPLACE_1_END__


    public static List<String> getFields() {
    	ArrayList<String> fieldNames = new ArrayList<String>();
    	String fieldName = null;
    	Integer i = 1;
    	
        while(true) { 
    		fieldName = PlayersAroundMe.getSchema().getFieldName(i);
    		if (fieldName == null) {
    			break;
    		}
    		fieldNames.add(fieldName);
    		i++;
    	}
    	return fieldNames;
    }

    public static PlayersAroundMe parseFrom(byte[] bytes) {
    	PlayersAroundMe message = new PlayersAroundMe();
    	ProtobufIOUtil.mergeFrom(bytes, message, RuntimeSchema.getSchema(PlayersAroundMe.class));
    	return message;
    }
    	
    public byte[] toByteArray(String encoding) {
    	if (encoding.equals("protobuf")) {
    		return toProtobuf();
    	} else if (encoding.equals("json")) {
    		return toJson();
    	} else {
    		throw new RuntimeException("No encoding specified");
    	}
    }

    public byte[] toJson() {
    	boolean numeric = false;
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	try {
    		JsonIOUtil.writeTo(out, this, PlayersAroundMe.getSchema(), numeric);
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new RuntimeException("Json encoding failed");
    	}
    	return out.toByteArray();
    }
    		
    public byte[] toProtobuf() {
    	LinkedBuffer buffer = LinkedBuffer.allocate(1024);
    	byte[] bytes = null;

    	try {
    		bytes = ProtobufIOUtil.toByteArray(this, RuntimeSchema.getSchema(PlayersAroundMe.class), buffer);
    		buffer.clear();
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException("Protobuf encoding failed");
    	}
    	return bytes;
    }

}
