package org.ztouhou.mcmod.decoration.item;

import org.ztouhou.mcmod.decoration.Decoration;

import com.google.common.collect.Multimap;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rikka.librikka.item.ItemBase;

public class ItemFireExtinguisher extends ItemBase {
	public ItemFireExtinguisher() {
		super("fireextinguisher", (new Item.Properties()).group(Decoration.itemGroup).maxDamage(40));
	}
	
	////////////
	/// Weapon
	////////////
	@Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
        	double damage, speed;
        	if (stack.getDamage() < stack.getMaxDamage()) {
        		damage = 12;
        		speed = -3.5;
        	}else {
        		damage = 1;
        		speed = 0;
        	}
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", damage, AttributeModifier.Operation.ADDITION));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", speed, AttributeModifier.Operation.ADDITION));
        }

        return multimap;
    }
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		return false;
	}

	@Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (stack.getDamage() < stack.getMaxDamage())
			stack.damageItem(1, attacker, (attacker_var) -> {
				attacker_var.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		      });
		
        return true;
    }
	
	@Override
	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		return false;
	}
	
	/////////////////////
	/// FireExtinguisher
	/////////////////////
	@Override
    public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		
        ItemStack stack = player.getHeldItem(hand);
        if(stack == null)
        	return ActionResultType.FAIL;
        
        if (stack.isEmpty())
        	return ActionResultType.FAIL;
        
        if (stack.getDamage() + 2 > stack.getMaxDamage())
        	return ActionResultType.FAIL;
        
    	//Extinguish fire on blocks
        for(int i=-2;i<3;i++){
            for(int j=-2;j<3;j++){
                for(int k=-2;k<3;k++){
                	for (Direction side: Direction.values())
                		world.extinguishFire(player, pos.add(i, j, k), side);
                }
            }
        }

        //Extinguish fire on entities
        for (Entity entity: world.getEntitiesWithinAABB(Entity.class, (new AxisAlignedBB(-2, -2, -2, 2, 2, 2)).offset(pos))) {
        	entity.extinguish();
        }
        stack.damageItem(2, player, (attacker_var) -> {
			attacker_var.sendBreakAnimation(EquipmentSlotType.MAINHAND);
	      });
        
        if (!world.isRemote) {
        	spawnFoam(world, player.getPosX(), player.getPosY() - 1, player.getPosZ());
        	spawnFoam(world, player.getPosX(), player.getPosY(), player.getPosZ());
        	spawnFoam(world, player.getPosX(), player.getPosY() + 1, player.getPosZ());
        	spawnFoam(world, player.getPosX(), player.getPosY() + 2, player.getPosZ());
        }
        
		return ActionResultType.SUCCESS;
    }
	
	public static void spawnFoam(World world, double posX, double posY, double posZ) {
		AreaEffectCloudEntity entityareaeffectcloud = new AreaEffectCloudEntity(world, posX, posY, posZ);
        entityareaeffectcloud.setRadius(3.0F);
        entityareaeffectcloud.setRadiusOnUse(-0.5F);
        entityareaeffectcloud.setWaitTime(1);
        entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
        entityareaeffectcloud.setColor(0xF0FFFFFF);
        entityareaeffectcloud.setDuration(25);
    	world.addEntity(entityareaeffectcloud);
	}
}
