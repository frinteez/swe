package artcreator.creator;

import artcreator.creator.impl.CreatorImpl;
import artcreator.creator.port.Creator;
import artcreator.domain.DomainFactory;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.StateMachine;
import artcreator.statemachine.port.State.S;

public class CreatorFacade implements CreatorFactory, Creator {

	private CreatorImpl creator;
	private StateMachine stateMachine;

	@Override
	public Creator creator() {
		if (this.creator == null) {
			this.stateMachine = StateMachineFactory.FACTORY.stateMachine();
			this.creator = new CreatorImpl(stateMachine, DomainFactory.FACTORY.domain());
		}
		return this;
	}

	private boolean allowed(S required) {
		return this.stateMachine != null
				&& this.stateMachine.getState() != null
				&& this.stateMachine.getState().isSubStateOf(required);
	}

	@Override
	public synchronized void selectImage(String imagePath) {
		if (!allowed(S.CREATE_TEMPLATE)) return;
		this.creator.selectImage(imagePath);
	}

	@Override
	public synchronized void setRasterParameters(String rasterParams) {
		if (!allowed(S.CREATE_TEMPLATE)) return;
		this.creator.setRasterParameters(rasterParams);
	}

	@Override
	public synchronized void setMaterialProfile(String materialProfile) {
		if (!allowed(S.CREATE_TEMPLATE)) return;
		this.creator.setMaterialProfile(materialProfile);
	}

	@Override
	public synchronized void generateTemplate() {
		if (!allowed(S.CREATE_TEMPLATE)) return;
		this.creator.generateTemplate();
	}

	@Override
	public synchronized void acceptTemplate() {
		if (!allowed(S.CREATE_TEMPLATE)) return;
		this.creator.acceptTemplate();
	}
}
