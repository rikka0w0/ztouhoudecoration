package org.ztouhou.mcmod.decoration.item;

import com.google.common.collect.Multimap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;

public class ItemFireExtinguisher extends ItemBase implements ISimpleTexture {
	public ItemFireExtinguisher() {
		super("fireextinguisher", false);
		setMaxStackSize(1);
		setMaxDamage(40);
		setCreativeTab(CreativeTabs.REDSTONE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getIconName(int damage) {
		return "item_fireextinguisher";
	}
	
	////////////
	/// Weapon
	////////////
	@Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
        	double damage, speed;
        	if (stack.getItemDamage() < stack.getMaxDamage()) {
        		damage = 12;
        		speed = -3.5;
        	}else {
        		damage = 1;
        		speed = 0;
        	}
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", damage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", speed, 0));
        }

        return multimap;
    }
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		return false;
	}

	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (stack.getItemDamage() < stack.getMaxDamage())
			stack.damageItem(1, attacker);
		
        return true;
    }
	
	@Override
	public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
		return false;
	}
	
	/////////////////////
	/// FireExtinguisher
	/////////////////////
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {       
        ItemStack stack = player.getHeldItem(hand);
        if(stack == null)
        	return EnumActionResult.FAIL;
        
        if (stack.isEmpty())
        	return EnumActionResult.FAIL;
        
        if (stack.getItemDamage() + 2 > stack.getMaxDamage())
        	return EnumActionResult.FAIL;
        
    	//Extinguish fire on blocks
        for(int i=-2;i<3;i++){
            for(int j=-2;j<3;j++){
                for(int k=-2;k<3;k++){
                	for (EnumFacing side: EnumFacing.VALUES)
                		world.extinguishFire(player, pos.add(i, j, k), side);
                }
            }
        }

        //Extinguish fire on entities
        for (Entity entity: world.getEntitiesWithinAABB(Entity.class, (new AxisAlignedBB(-2, -2, -2, 2, 2, 2)).offset(pos))) {
        	entity.extinguish();
        }
        stack.damageItem(2, player);
        
        if (!world.isRemote) {
        	spawnFoam(world, player.posX, player.posY - 1, player.posZ);
        	spawnFoam(world, player.posX, player.posY, player.posZ);
        	spawnFoam(world, player.posX, player.posY + 1, player.posZ);
        	spawnFoam(world, player.posX, player.posY + 2, player.posZ);
        }
        
		return EnumActionResult.SUCCESS;
    }
	
	public static void spawnFoam(World world, double posX, double posY, double posZ) {
    	EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(world, posX, posY, posZ);
        entityareaeffectcloud.setRadius(3.0F);
        entityareaeffectcloud.setRadiusOnUse(-0.5F);
        entityareaeffectcloud.setWaitTime(1);
        entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
        entityareaeffectcloud.setColor(0xF0FFFFFF);
        entityareaeffectcloud.setDuration(25);
    	world.spawnEntity(entityareaeffectcloud);
	}
}
