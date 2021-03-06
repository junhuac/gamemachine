package pvp_game;

import io.gamemachine.core.ActorUtil;
import io.gamemachine.core.GameGrid;
import io.gamemachine.core.GameMessageActor;
import io.gamemachine.core.Grid;
import io.gamemachine.core.PlayerCommands;
import io.gamemachine.messages.Attack;
import io.gamemachine.messages.DataRequest;
import io.gamemachine.messages.GameMessage;
import io.gamemachine.messages.PlayerSkill;
import io.gamemachine.messages.StatusEffectTarget;
import io.gamemachine.messages.TrackData;
import io.gamemachine.messages.Vitals;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import akka.actor.ActorSelection;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class CombatHandler extends GameMessageActor {

	public static String name = CombatHandler.class.getSimpleName();
	LoggingAdapter logger = Logging.getLogger(getContext().system(), this);
	
	public static ConcurrentHashMap<String, PlayerSkill> playerSkills = new ConcurrentHashMap<String, PlayerSkill>();
	public static ConcurrentHashMap<String, Vitals> playerVitals = new ConcurrentHashMap<String, Vitals>();
	
	private ActorSelection effectHandler;
	
	@Override
	public void awake() {
		effectHandler = ActorUtil.getSelectionByName(StatusEffectHandler.name);
		List<PlayerSkill> skills = PlayerSkill.db().where("player_skill_character_id = ?", "global");
		for (PlayerSkill playerSkill : skills) {
			playerSkills.put(playerSkill.id, playerSkill);
			logger.warning("Loading skill "+playerSkill.id);
		}
	}

	@Override
	public void onGameMessage(GameMessage gameMessage) {
		if (gameMessage.hasAttack()) {
			doAttack(gameMessage.attack);
		} else if (gameMessage.hasDataRequest()) {
			effectHandler.tell(gameMessage.dataRequest, getSelf());
		}
	}


	private void sendAttack(Attack attack) {
		GameMessage msg = new GameMessage();

		Grid grid = GameGrid.getGameGrid("mygame", "default");
		for (TrackData trackData : grid.getAll()) {
			if (!attack.attacker.equals(trackData.id)) {
				msg.attack = attack;
				PlayerCommands.sendGameMessage(msg, trackData.id);
			}
		}
	}

	private void doAttack(Attack attack) {
		logger.warning("Attack skill "+attack.skill);
		PlayerSkill skill = playerSkills.get(attack.skill);
		StatusEffectTarget target = new StatusEffectTarget();
		target.location = attack.targetLocation;
		target.range = skill.range;
		target.skill = skill.id;
		target.origin = attack.attacker;
		
		if (skill.damageType.equals("aoe")) {
			target.target = "aoe";
		} else if (skill.damageType.equals("pbaoe")) {
			target.target = "aoe";
		} else if (skill.damageType.equals("st")) {
			target.target = attack.target;
		}
		if (target.target.equals("aoe")) {
			if (target.location.xi == null) {
				target.location.xi = 0;
			}
			if (target.location.yi == null) {
				target.location.yi = 0;
			}
			if (target.location.zi == null) {
				target.location.zi = 0;
			}
		}
		
		Vitals vitals = playerVitals.get(target.origin);
		if (vitals != null) {
			if (skill.resource.equals("stamina")) {
				vitals.stamina -= skill.resourceCost;
			} else if (skill.resource.equals("magic")) {
				vitals.magic -= skill.resourceCost;
			}
		}
		
		effectHandler.tell(target, getSelf());
		sendAttack(attack);
	}

	@Override
	public void onPlayerDisconnect(String playerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTick(String message) {
		// TODO Auto-generated method stub
		
	}

	
}
