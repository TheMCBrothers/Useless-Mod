package tk.themcbros.uselessmod.closet;

import net.minecraftforge.eventbus.api.Event;

public class BeddingRegistryEvent extends Event {

	private IClosetRegistry casingRegistry;
    private IClosetRegistry beddingRegistry;
    
    public BeddingRegistryEvent(IClosetRegistry casingRegistry, IClosetRegistry beddingRegistry) {
        this.casingRegistry = casingRegistry;
        this.beddingRegistry = beddingRegistry;
    }
    
    public IClosetRegistry getBeddingRegistry() {
        return this.beddingRegistry;
    }
    
    public IClosetRegistry getCasingRegistry() {
        return this.casingRegistry;
    }
	
}
