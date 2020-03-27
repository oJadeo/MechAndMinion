package damagecard;

import card.base.CmdCard;
import card.base.OnGoing;
import logic.CardSprite;
import logic.Direction;
import token.Mech;

public class Rotate180Card extends CmdCard implements OnGoing{
	public Rotate180Card(Mech programmedMech) {
		this.setSpriteValue(CardSprite.TURN_AROUND);
		this.setProgrammedMech(programmedMech);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		switch(this.getProgrammedMech().getDirection()) {
		case UP:
			this.getProgrammedMech().setDirection(Direction.DOWN);
			break;
		case DOWN:
			this.getProgrammedMech().setDirection(Direction.UP);
			break;
		case LEFT:
			this.getProgrammedMech().setDirection(Direction.RIGHT);
			break;
		case RIGHT:
			this.getProgrammedMech().setDirection(Direction.LEFT);
			break;
		default:
			break;
			
		}
		
	}

	@Override
	public void setSpriteValue(int spriteValue) {
		// TODO Auto-generated method stub
		this.spriteValue = spriteValue;
	}
	

}
