module GameMachine
  class ActorBuilder

    attr_reader :name

    def initialize(*args)
      @klass = args.shift
      @name = @klass.name
      @create_hashring = false
      @hashring_size = 0
      @props = JavaLib::Props.new(ActorFactory.new(@klass,args))
    end

    def with_name(name)
      @name = name
      self
    end

    def with_router(router_class,num_routers)
      @props = @props.with_router(
        router_class.new(num_routers)
      )
      self
    end

    def distributed(count)
      @create_hashring = true
      @hashring_size = count
      self
    end

    def start
      if @create_hashring
        hashring = create_hashring(@hashring_size)
        hashring.buckets.each do |bucket_name|
          actor_system.actor_of(@props, bucket_name)
        end
      else
        actor_system.actor_of(@props, @name)
      end
    end


    private

    def actor_system
      Server.instance.actor_system
    end

    def create_hashring(bucket_count)
        hashring = Hashring.new(
          Settings.servers.keys,
          @name
        )
        @klass.add_hashring(@name,hashring)
        hashring
    end

  end
end