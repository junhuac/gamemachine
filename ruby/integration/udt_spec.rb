require 'integration_helper'

module GameMachine
  CHARS = [*('a'..'z'),*('0'..'9')].flatten
  STR = Array.new(100) {|i| CHARS.sample}.join
  STR2 = Array.new(1000) {|i| CHARS.sample}.join

  describe "basic" do 


    let(:player) do
      player = Player.new
      player.set_id('2')
      player.set_authtoken('authorized')
    end

    let(:entity) do
      entity = Entity.new
      entity.set_id(STR.to_java_string)
      entity.set_player(player)
      echo_test = EchoTest.new.set_message('testing')
      entity.set_echo_test(echo_test)
      entity
    end

    let(:large_entity) do
      entity = Entity.new
      entity.set_id(STR2.to_java_string)
      entity.set_player(player)
      echo_test = EchoTest.new.set_message('testing')
      entity.set_echo_test(echo_test)
      entity
    end

    let(:entity_list) do 
      entity_list = EntityList.new
      entity_list.add_entity(entity)
      entity_list.set_player(player)
      entity_list
    end

    let(:large_entity_list) do 
      entity_list = EntityList.new
      10.times do
        entity_list.add_entity(entity)
      end
      entity_list.set_player(player)
      entity_list
    end

    let(:client) {Client.new(:seed01)}

    context "test" do
      describe "udt" do

        it "should send/receive" do
          address = JavaLib::InetSocketAddress.new(Settings.servers.seed01.udt.host, Settings.servers.seed01.udt.port)
          bytes = entity_list.to_byte_array
          measure(10,10000) do
            unless Thread.current['s']
              s = JavaLib::SocketUDT.new(JavaLib::TypeUDT::DATAGRAM)
              s.setBlocking(true)
              s.connect(address)
              Thread.current['s'] = s
            end
            Thread.current['s'].send(bytes)
            array = Java::byte[1024].new
            Thread.current['s'].receive(array)
          end
        end

      end
    end

  end
end

