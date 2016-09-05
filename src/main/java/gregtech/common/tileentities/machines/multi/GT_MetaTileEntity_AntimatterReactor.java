package gregtech.common.tileentities.machines.multi;

import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Dyes;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures;
import gregtech.api.gui.GT_Container_MultiMachine;
import gregtech.api.gui.GT_GUIContainer_MultiMachine;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Energy;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gregtech.common.gui.GT_GUIContainer_FusionReactor;
import net.minecraft.block.Block;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collection;

public class GT_MetaTileEntity_AntimatterReactor extends GT_MetaTileEntity_MultiBlockBase {
	protected int fuelConsumption = 0;
    protected int fuelValue = 0;
    protected int fuelRemaining = 0;
    protected boolean boostEu = false;
    protected int xDir = 0, zDir = 0;
    public GT_Recipe mLastRecipe;
	
    public GT_MetaTileEntity_AntimatterReactor(int aID, String aName, String aNameRegional, int tier) {
        super(aID, aName, aNameRegional);
    }

    public GT_MetaTileEntity_AntimatterReactor(String aName) {
        super(aName);
    }
    
    public String[] getDescription() {
        return new String[]{
                "Controller Block for the Antimatter Reactor",
                "Size(WxHxD): 7x7x7 (Sphere)",
                "Blah-Blah-Blah",
                "Blah-Blah-Blah",
                "Blah-Blah-Blah",
                "Blah-Blah-Blah",
                "Blah-Blah-Blah",
                "Blah-Blah-Blah",
                "Blah-Blah-Blah",
                "Blah-Blah-Blah"};
    }
    
    //Set textures for main block
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        ITexture[] sTexture;
        if (aSide == aFacing) {
            sTexture = new ITexture[]{new GT_RenderedTexture(Textures.BlockIcons.MACHINE_CASING_FUSION_GLASS, Dyes.getModulation(-1, Dyes._NULL.mRGBa)), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_FUSION1)};
        } else {
            if (!aActive) {
                sTexture = new ITexture[]{new GT_RenderedTexture(Textures.BlockIcons.MACHINE_CASING_FUSION_GLASS, Dyes.getModulation(-1, Dyes._NULL.mRGBa))};
            } else {
                sTexture = new ITexture[]{new GT_RenderedTexture(Textures.BlockIcons.MACHINE_CASING_FUSION_GLASS_YELLOW, Dyes.getModulation(-1, Dyes._NULL.mRGBa))};
            }
        }
        return sTexture;
    }
    
    @Override
    public boolean isCorrectMachinePart(ItemStack aStack) {
        return true;
    }
    
    @Override
    public Object getServerGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_Container_MultiMachine(aPlayerInventory, aBaseMetaTileEntity);
    }

    @Override
    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_GUIContainer_FusionReactor(aPlayerInventory, aBaseMetaTileEntity, getLocalName(), "FusionComputer.png", GT_Recipe.GT_Recipe_Map.sFusionRecipes.mNEIName);
    }
    
    //CHANGE!!! Check Recipes. Compare this two
     @Override
    public boolean checkRecipe(ItemStack aStack) {
        /*ArrayList<FluidStack> tFluidList = getStoredFluids();
        for (int i = 0; i < tFluidList.size() - 1; i++) {
            for (int j = i + 1; j < tFluidList.size(); j++) {
                if (GT_Utility.areFluidsEqual((FluidStack) tFluidList.get(i), (FluidStack) tFluidList.get(j))) {
                    if (((FluidStack) tFluidList.get(i)).amount >= ((FluidStack) tFluidList.get(j)).amount) {
                        tFluidList.remove(j--);
                    } else {
                        tFluidList.remove(i--);
                        break;
                    }
                }
            }
        }
        if (tFluidList.size() > 1) {
            FluidStack[] tFluids = tFluidList.toArray(new FluidStack[tFluidList.size()]);
            GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sFusionRecipes.findRecipe(this.getBaseMetaTileEntity(), this.mLastRecipe, false, GT_Values.V[8], tFluids, new ItemStack[]{});
            if (tRecipe == null && !mRunningOnLoad) {
                //turnCasingActive(false);
                this.mLastRecipe = null;
                return false;
            }
            if (mRunningOnLoad || tRecipe.isRecipeInputEqual(true, tFluids, new ItemStack[]{})) {
                this.mLastRecipe = tRecipe;
                this.mEUt = this.mLastRecipe.mEUt;
                this.mMaxProgresstime = this.mLastRecipe.mDuration;
                this.mEfficiencyIncrease = 10000;
                this.mOutputFluids = this.mLastRecipe.mFluidOutputs;
                //turnCasingActive(true);
                mRunningOnLoad = false;
                return true;
            }
        }*/
        return false;
    }
    
    /* Activate Block Casing change textures
    public boolean turnCasingActive(boolean status) {
        if (this.mDynamoHatches != null) {
            for (GT_MetaTileEntity_Hatch_Dynamo hatch : this.mDynamoHatches) {
                hatch.mMachineBlock = status ? (byte) 52 : (byte) 53;
            }
        }
        if (this.mInputHatches != null) {
            for (GT_MetaTileEntity_Hatch_Input hatch : this.mInputHatches) {
                hatch.mMachineBlock = status ? (byte) 52 : (byte) 53;
            }
        }
        return true;
    }*/
    
    //Main logic construct check
    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
    	
        xDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getBackFacing()).offsetX;
        zDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getBackFacing()).offsetZ;
    	
    	int xCenter = getBaseMetaTileEntity().getXCoord() + xDir * 7;
        int yCenter = getBaseMetaTileEntity().getYCoord();
        int zCenter = getBaseMetaTileEntity().getZCoord() + zDir * 7;
                
        if ((checkRingInYPlane(xCenter, yCenter, zCenter)) && (checkRingInVPlane(xCenter, yCenter, zCenter)) //Check rings (Dyson Sphere)
        		&& (checkKineticCoils(xCenter, yCenter, zCenter))//Check Kinetic Ring
        		&& (checkMagneticCoilsInXPlane(xCenter, yCenter, zCenter)) && (checkMagneticCoilsInYPlane(xCenter, yCenter, zCenter)) // Check Magnetic Ring
        		&& (checkCoreCasings(xCenter, yCenter, zCenter)) && (checkLoadingChamber(xCenter, yCenter, zCenter)) //Check Core Components
        		&& (checkIntermixChamber(xCenter, yCenter, zCenter)) // Check Intermix Chamber
        		&& (addIfEnergyExtractor(xCenter + xDir * 7, yCenter, zCenter + zDir * 7 , aBaseMetaTileEntity))
        		/*&& (this.mDynamoHatches.size() >= 1) && (this.mInputHatches.size() >= 2)*/) {
           /* if (this.mDynamoHatches != null) {
                for (int i = 0; i < this.mDynamoHatches.size(); i++) {
                    if (this.mDynamoHatches.get(i).mTier < 9)
                        return false;
                }
            }
            if (this.mInputHatches != null) {
                for (int i = 0; i < this.mInputHatches.size(); i++) {
                    if (this.mInputHatches.get(i).mTier < 9)
                        return false;
                }
            }*/
            mWrench = true;
            mScrewdriver = true;
            mSoftHammer = true;
            mHardHammer = true;
            mSolderingTool = true;
            mCrowbar = true;
            return true;
        }
        return false;
    	
    }
    
    //Check horizontal casing rings (Dyson Sphere)
    private boolean checkRingInYPlane(int aX, int aY, int aZ) {
        return (isAdvancedMachineCasing(aX + 7, aY - 1, aZ - 1)) && (isAdvancedMachineCasing(aX + 7, aY + 1, aZ - 1)) && (isAdvancedMachineCasing(aX + 7, aY - 1, aZ + 1)) && (isAdvancedMachineCasing(aX + 7, aY + 1, aZ + 1))
        		&& (isAdvancedMachineCasing(aX + 7, aY, aZ - 2)) && (isAdvancedMachineCasing(aX + 7, aY, aZ + 2)) && (isAdvancedMachineCasing(aX + 6, aY, aZ - 3)) && (isAdvancedMachineCasing(aX + 6, aY, aZ + 3))
        		&& (isAdvancedMachineCasing(aX + 5, aY, aZ - 4)) && (isAdvancedMachineCasing(aX + 5, aY, aZ + 4)) && (isAdvancedMachineCasing(aX + 4, aY, aZ - 5)) && (isAdvancedMachineCasing(aX + 4, aY, aZ + 5))
        		&& (isAdvancedMachineCasing(aX + 3, aY, aZ - 6)) && (isAdvancedMachineCasing(aX + 3, aY, aZ + 6)) && (isAdvancedMachineCasing(aX + 2, aY, aZ - 7)) && (isAdvancedMachineCasing(aX + 2, aY, aZ + 7))
        		&& (isAdvancedMachineCasing(aX + 1, aY - 1, aZ - 7)) && (isAdvancedMachineCasing(aX + 1, aY - 1, aZ + 7)) && (isAdvancedMachineCasing(aX + 1, aY + 1, aZ - 7)) && (isAdvancedMachineCasing(aX + 1, aY + 1, aZ + 7))
        		&& (isAdvancedMachineCasing(aX, aY - 1, aZ - 7)) && (isAdvancedMachineCasing(aX, aY - 1, aZ + 7)) && (isAdvancedMachineCasing(aX, aY + 1, aZ - 7)) && (isAdvancedMachineCasing(aX, aY + 1, aZ + 7))
        		&& (isAdvancedMachineCasing(aX - 1, aY - 1, aZ - 7)) && (isAdvancedMachineCasing(aX - 1, aY - 1, aZ + 7))
        		&& (isAdvancedMachineCasing(aX - 1, aY + 1, aZ - 7)) && (isAdvancedMachineCasing(aX - 1, aY + 1, aZ + 7)) && (isAdvancedMachineCasing(aX - 2, aY, aZ - 7)) && (isAdvancedMachineCasing(aX - 2, aY, aZ + 7))
        		&& (isAdvancedMachineCasing(aX - 3, aY, aZ - 6)) && (isAdvancedMachineCasing(aX - 3, aY, aZ + 6)) && (isAdvancedMachineCasing(aX - 4, aY, aZ - 5)) && (isAdvancedMachineCasing(aX - 4, aY, aZ + 5))
        		&& (isAdvancedMachineCasing(aX - 5, aY, aZ - 4)) && (isAdvancedMachineCasing(aX - 5, aY, aZ + 4)) && (isAdvancedMachineCasing(aX - 6, aY, aZ - 3)) && (isAdvancedMachineCasing(aX - 6, aY, aZ + 3))
        		&& (isAdvancedMachineCasing(aX - 7, aY, aZ - 2)) && (isAdvancedMachineCasing(aX - 7, aY, aZ + 2)) && (isAdvancedMachineCasing(aX - 7, aY - 1, aZ - 1)) && (isAdvancedMachineCasing(aX - 7, aY - 1, aZ + 1))
        		&& (isAdvancedMachineCasing(aX - 7, aY + 1, aZ - 1)) && (isAdvancedMachineCasing(aX - 7, aY + 1, aZ + 1))
        		&& (((isAdvancedMachineCasing(aX + 1, aY, aZ - 7)) && (isAdvancedMachineCasing(aX + 1, aY, aZ + 7)) && (isAdvancedMachineCasing(aX, aY, aZ - 7)) && (isAdvancedMachineCasing(aX, aY, aZ + 7)) && (isAdvancedMachineCasing(aX - 1, aY, aZ - 7)) && (isAdvancedMachineCasing(aX - 1, aY, aZ + 7)))
        		|| ((isAdvancedMachineCasing(aX - 7, aY, aZ + 1)) && (isAdvancedMachineCasing(aX + 7, aY, aZ + 1)) && (isAdvancedMachineCasing(aX - 7, aY, aZ)) && (isAdvancedMachineCasing(aX + 7, aY, aZ)) && (isAdvancedMachineCasing(aX - 7, aY, aZ - 1)) && (isAdvancedMachineCasing(aX + 7, aY, aZ -1))));
    } 
    
    //Check vertical casing rings (Dyson Sphere)
    private boolean checkRingInVPlane(int aX, int aY, int aZ) {
        return (isAdvancedMachineCasing(aX, aY - 7, aZ)) && (isAdvancedMachineCasing(aX, aY + 7, aZ))
        		&& (((isAdvancedMachineCasing(aX + 7, aY - 1, aZ)) && (isAdvancedMachineCasing(aX + 7, aY + 1, aZ)) && (isAdvancedMachineCasing(aX + 7, aY - 2, aZ)) && (isAdvancedMachineCasing(aX + 7, aY + 2, aZ))
        		&& (isAdvancedMachineCasing(aX + 7, aY - 3, aZ)) && (isAdvancedMachineCasing(aX + 7, aY + 3, aZ)) && (isAdvancedMachineCasing(aX + 6, aY - 4, aZ)) && (isAdvancedMachineCasing(aX + 6, aY + 4, aZ))
        		&& (isAdvancedMachineCasing(aX + 5, aY - 5, aZ)) && (isAdvancedMachineCasing(aX + 5, aY + 5, aZ)) && (isAdvancedMachineCasing(aX + 4, aY - 6, aZ)) && (isAdvancedMachineCasing(aX + 4, aY + 6, aZ))
        		&& (isAdvancedMachineCasing(aX + 3, aY - 7, aZ)) && (isAdvancedMachineCasing(aX + 3, aY + 7, aZ)) && (isAdvancedMachineCasing(aX + 2, aY - 7, aZ)) && (isAdvancedMachineCasing(aX + 2, aY + 7, aZ))
        		&& (isAdvancedMachineCasing(aX + 1, aY - 7, aZ)) && (isAdvancedMachineCasing(aX + 1, aY + 7, aZ))
        		&& (isAdvancedMachineCasing(aX - 1, aY - 7, aZ)) && (isAdvancedMachineCasing(aX - 1, aY + 7, aZ)) && (isAdvancedMachineCasing(aX - 2, aY - 7, aZ)) && (isAdvancedMachineCasing(aX - 2, aY + 7, aZ))
        		&& (isAdvancedMachineCasing(aX - 3, aY - 7, aZ)) && (isAdvancedMachineCasing(aX - 3, aY + 7, aZ)) && (isAdvancedMachineCasing(aX - 4, aY - 6, aZ)) && (isAdvancedMachineCasing(aX - 4, aY + 6, aZ))
        		&& (isAdvancedMachineCasing(aX - 5, aY - 5, aZ)) && (isAdvancedMachineCasing(aX - 5, aY + 5, aZ)) && (isAdvancedMachineCasing(aX - 6, aY - 4, aZ)) && (isAdvancedMachineCasing(aX - 6, aY + 4, aZ))
        		&& (isAdvancedMachineCasing(aX - 7, aY - 3, aZ)) && (isAdvancedMachineCasing(aX - 7, aY + 3, aZ)) && (isAdvancedMachineCasing(aX - 7, aY - 2, aZ)) && (isAdvancedMachineCasing(aX - 7, aY + 2, aZ))
        		&& (isAdvancedMachineCasing(aX - 7, aY - 1, aZ)) && (isAdvancedMachineCasing(aX - 7, aY + 1, aZ)))
        		|| ((isAdvancedMachineCasing(aX, aY - 1, aZ + 7)) && (isAdvancedMachineCasing(aX, aY + 1, aZ + 7)) && (isAdvancedMachineCasing(aX, aY - 2, aZ + 7)) && (isAdvancedMachineCasing(aX, aY + 2, aZ + 7))
                && (isAdvancedMachineCasing(aX, aY - 3, aZ + 7)) && (isAdvancedMachineCasing(aX, aY + 3, aZ + 7)) && (isAdvancedMachineCasing(aX, aY - 4, aZ + 6)) && (isAdvancedMachineCasing(aX, aY + 4, aZ + 6))
                && (isAdvancedMachineCasing(aX, aY - 5, aZ + 5)) && (isAdvancedMachineCasing(aX, aY + 5, aZ + 5)) && (isAdvancedMachineCasing(aX, aY - 6, aZ + 4)) && (isAdvancedMachineCasing(aX, aY + 6, aZ + 4))
                && (isAdvancedMachineCasing(aX, aY - 7, aZ + 3)) && (isAdvancedMachineCasing(aX, aY + 7, aZ + 3)) && (isAdvancedMachineCasing(aX, aY - 7, aZ + 2)) && (isAdvancedMachineCasing(aX, aY + 7, aZ + 2))
                && (isAdvancedMachineCasing(aX, aY - 7, aZ + 1)) && (isAdvancedMachineCasing(aX, aY + 7, aZ + 1))
                && (isAdvancedMachineCasing(aX, aY - 7, aZ - 1)) && (isAdvancedMachineCasing(aX, aY + 7, aZ - 1)) && (isAdvancedMachineCasing(aX, aY - 7, aZ - 2)) && (isAdvancedMachineCasing(aX, aY + 7, aZ - 2))
                && (isAdvancedMachineCasing(aX, aY - 7, aZ - 3)) && (isAdvancedMachineCasing(aX, aY + 7, aZ - 3)) && (isAdvancedMachineCasing(aX, aY - 6, aZ - 4)) && (isAdvancedMachineCasing(aX, aY + 6, aZ - 4))
                && (isAdvancedMachineCasing(aX, aY - 5, aZ - 5)) && (isAdvancedMachineCasing(aX, aY + 5, aZ - 5)) && (isAdvancedMachineCasing(aX, aY - 4, aZ - 6)) && (isAdvancedMachineCasing(aX, aY + 4, aZ - 6))
                && (isAdvancedMachineCasing(aX, aY - 3, aZ - 7)) && (isAdvancedMachineCasing(aX, aY + 3, aZ - 7)) && (isAdvancedMachineCasing(aX, aY - 2, aZ - 7)) && (isAdvancedMachineCasing(aX, aY + 2, aZ - 7))
                && (isAdvancedMachineCasing(aX, aY - 1, aZ - 7)) && (isAdvancedMachineCasing(aX, aY + 1, aZ - 7))));
    }
    
    //Check kinetic coils
    private boolean checkKineticCoils(int aX, int aY, int aZ) {
    	return (isKineticCoil(aX + 6, aY, aZ)) && (isKineticCoil(aX - 6, aY, aZ)) && (isKineticCoil(aX + 6, aY, aZ + 1)) && (isKineticCoil(aX + 6, aY, aZ - 1))// Horisontal Ring
    			&& (isKineticCoil(aX + 6, aY, aZ + 2)) && (isKineticCoil(aX + 6, aY, aZ - 2)) && (isKineticCoil(aX + 5, aY, aZ + 3)) && (isKineticCoil(aX + 5, aY, aZ - 3))
    			&& (isKineticCoil(aX + 4, aY, aZ + 4)) && (isKineticCoil(aX + 4, aY, aZ - 4)) && (isKineticCoil(aX + 3, aY, aZ + 5)) && (isKineticCoil(aX + 3, aY, aZ - 5))
    			&& (isKineticCoil(aX + 2, aY, aZ + 6)) && (isKineticCoil(aX + 2, aY, aZ - 6)) && (isKineticCoil(aX + 1, aY, aZ + 6)) && (isKineticCoil(aX + 1, aY, aZ - 6))
    			&& (isKineticCoil(aX, aY, aZ + 6)) && (isKineticCoil(aX, aY, aZ - 6)) && (isKineticCoil(aX - 1, aY, aZ + 6)) && (isKineticCoil(aX - 1, aY, aZ - 6))
    			&& (isKineticCoil(aX - 2, aY, aZ + 6)) && (isKineticCoil(aX - 2, aY, aZ - 6)) && (isKineticCoil(aX - 3, aY, aZ + 5)) && (isKineticCoil(aX - 3, aY, aZ - 5))
    			&& (isKineticCoil(aX - 4, aY, aZ + 4)) && (isKineticCoil(aX - 4, aY, aZ - 4)) && (isKineticCoil(aX - 5, aY, aZ + 3)) && (isKineticCoil(aX - 5, aY, aZ - 3))
    			&& (isKineticCoil(aX - 6, aY, aZ + 2)) && (isKineticCoil(aX - 6, aY, aZ - 2)) && (isKineticCoil(aX - 6, aY, aZ + 1)) && (isKineticCoil(aX - 6, aY, aZ - 1))
    			&& (((isKineticCoil(aX + 5, aY, aZ)) && (isKineticCoil(aX - 5, aY, aZ)) && (isKineticCoil(aX + 5, aY + 1, aZ)) && (isKineticCoil(aX + 5, aY - 1, aZ))//Vertical Ring in X Plane
    			&& (isKineticCoil(aX + 5, aY + 2, aZ)) && (isKineticCoil(aX + 5, aY - 2, aZ)) && (isKineticCoil(aX + 4, aY + 3, aZ)) && (isKineticCoil(aX + 4, aY - 3, aZ))
    			&& (isKineticCoil(aX + 3, aY + 4, aZ)) && (isKineticCoil(aX + 3, aY - 4, aZ)) && (isKineticCoil(aX + 2, aY + 5, aZ)) && (isKineticCoil(aX + 2, aY - 5, aZ))
    			&& (isKineticCoil(aX + 1, aY + 5, aZ)) && (isKineticCoil(aX + 1, aY - 5, aZ)) && (isKineticCoil(aX, aY + 5, aZ)) && (isKineticCoil(aX, aY - 5, aZ))
    			&& (isKineticCoil(aX - 1, aY + 5, aZ)) && (isKineticCoil(aX - 1, aY - 5, aZ)) && (isKineticCoil(aX - 2, aY + 5, aZ)) && (isKineticCoil(aX - 2, aY - 5, aZ))
    			&& (isKineticCoil(aX - 3, aY + 4, aZ)) && (isKineticCoil(aX - 3, aY - 4, aZ)) && (isKineticCoil(aX - 4, aY + 3, aZ)) && (isKineticCoil(aX - 4, aY - 3, aZ))
    			&& (isKineticCoil(aX - 5, aY + 2, aZ)) && (isKineticCoil(aX - 5, aY - 2, aZ)) && (isKineticCoil(aX - 5, aY + 1, aZ)) && (isKineticCoil(aX - 5, aY - 1, aZ)))
    			|| ((isKineticCoil(aX, aY, aZ + 5)) && (isKineticCoil(aX, aY, aZ - 5)) && (isKineticCoil(aX, aY + 1, aZ + 5)) && (isKineticCoil(aX, aY - 1, aZ + 5))//Vertical Ring in Z Plane
    	    	&& (isKineticCoil(aX, aY + 2, aZ + 5)) && (isKineticCoil(aX, aY - 2, aZ + 5)) && (isKineticCoil(aX, aY + 3, aZ + 4)) && (isKineticCoil(aX, aY - 3, aZ + 4))
    	    	&& (isKineticCoil(aX, aY + 4, aZ + 3)) && (isKineticCoil(aX, aY - 4, aZ + 3)) && (isKineticCoil(aX, aY + 5, aZ + 2)) && (isKineticCoil(aX, aY - 5, aZ + 2))
    	    	&& (isKineticCoil(aX, aY + 5, aZ + 1)) && (isKineticCoil(aX, aY - 5, aZ + 1)) && (isKineticCoil(aX, aY + 5, aZ)) && (isKineticCoil(aX, aY - 5, aZ))
    	    	&& (isKineticCoil(aX, aY + 5, aZ - 1)) && (isKineticCoil(aX, aY - 5, aZ - 1)) && (isKineticCoil(aX, aY + 5, aZ - 2)) && (isKineticCoil(aX, aY - 5, aZ - 2))
    	    	&& (isKineticCoil(aX, aY + 4, aZ - 3)) && (isKineticCoil(aX, aY - 4, aZ - 3)) && (isKineticCoil(aX, aY + 3, aZ - 4)) && (isKineticCoil(aX, aY - 3, aZ - 4))
    	    	&& (isKineticCoil(aX, aY + 2, aZ - 5)) && (isKineticCoil(aX, aY - 2, aZ - 5)) && (isKineticCoil(aX, aY + 1, aZ - 5)) && (isKineticCoil(aX, aY - 1, aZ - 5))));
    }
    
    //Check magnetic coils
    private boolean checkMagneticCoilsInXPlane(int aX, int aY, int aZ) {
    	return (isMagneticCoil(aX + 7, aY + 1, aZ + 2)) && (isMagneticCoil(aX + 7, aY + 1, aZ - 2)) && (isMagneticCoil(aX + 7, aY - 1, aZ + 2)) && (isMagneticCoil(aX + 7, aY - 1, aZ - 2))//Horisontal
    			&& (isMagneticCoil(aX + 6, aY + 1, aZ + 3)) && (isMagneticCoil(aX + 6, aY + 1, aZ - 3)) && (isMagneticCoil(aX + 6, aY - 1, aZ + 3)) && (isMagneticCoil(aX + 6, aY - 1, aZ - 3))
    			&& (isMagneticCoil(aX + 5, aY + 1, aZ + 4)) && (isMagneticCoil(aX + 5, aY + 1, aZ - 4)) && (isMagneticCoil(aX + 5, aY - 1, aZ + 4)) && (isMagneticCoil(aX + 5, aY - 1, aZ - 4))
    			&& (isMagneticCoil(aX + 4, aY + 1, aZ + 5)) && (isMagneticCoil(aX + 4, aY + 1, aZ - 5)) && (isMagneticCoil(aX + 4, aY - 1, aZ + 5)) && (isMagneticCoil(aX + 4, aY - 1, aZ - 5))
    			&& (isMagneticCoil(aX + 3, aY + 1, aZ + 6)) && (isMagneticCoil(aX + 3, aY + 1, aZ - 6)) && (isMagneticCoil(aX + 3, aY - 1, aZ + 6)) && (isMagneticCoil(aX + 3, aY - 1, aZ - 6))
    			&& (isMagneticCoil(aX + 2, aY + 1, aZ + 7)) && (isMagneticCoil(aX + 2, aY + 1, aZ - 7)) && (isMagneticCoil(aX + 2, aY - 1, aZ + 7)) && (isMagneticCoil(aX + 2, aY - 1, aZ - 7))
    			&& (isMagneticCoil(aX - 7, aY + 1, aZ + 2)) && (isMagneticCoil(aX - 7, aY + 1, aZ - 2)) && (isMagneticCoil(aX - 7, aY - 1, aZ + 2)) && (isMagneticCoil(aX - 7, aY - 1, aZ - 2))//Horisontal
    			&& (isMagneticCoil(aX - 6, aY + 1, aZ + 3)) && (isMagneticCoil(aX - 6, aY + 1, aZ - 3)) && (isMagneticCoil(aX - 6, aY - 1, aZ + 3)) && (isMagneticCoil(aX - 6, aY - 1, aZ - 3))
    			&& (isMagneticCoil(aX - 5, aY + 1, aZ + 4)) && (isMagneticCoil(aX - 5, aY + 1, aZ - 4)) && (isMagneticCoil(aX - 5, aY - 1, aZ + 4)) && (isMagneticCoil(aX - 5, aY - 1, aZ - 4))
    			&& (isMagneticCoil(aX - 4, aY + 1, aZ + 5)) && (isMagneticCoil(aX - 4, aY + 1, aZ - 5)) && (isMagneticCoil(aX - 4, aY - 1, aZ + 5)) && (isMagneticCoil(aX - 4, aY - 1, aZ - 5))
    			&& (isMagneticCoil(aX - 3, aY + 1, aZ + 6)) && (isMagneticCoil(aX - 3, aY + 1, aZ - 6)) && (isMagneticCoil(aX - 3, aY - 1, aZ + 6)) && (isMagneticCoil(aX - 3, aY - 1, aZ - 6))
    			&& (isMagneticCoil(aX - 2, aY + 1, aZ + 7)) && (isMagneticCoil(aX - 2, aY + 1, aZ - 7)) && (isMagneticCoil(aX - 2, aY - 1, aZ + 7)) && (isMagneticCoil(aX - 2, aY - 1, aZ - 7));
    }
    
    private boolean checkMagneticCoilsInYPlane(int aX, int aY, int aZ) {
    	return (((isMagneticCoil(aX + 7, aY + 2, aZ + 1)) && (isMagneticCoil(aX + 7, aY - 2, aZ + 1)) && (isMagneticCoil(aX + 7, aY + 2, aZ - 1)) && (isMagneticCoil(aX + 7, aY - 2, aZ - 1))//Vertical
    			&& (isMagneticCoil(aX + 7, aY + 3, aZ + 1)) && (isMagneticCoil(aX + 7, aY - 3, aZ + 1)) && (isMagneticCoil(aX + 7, aY + 3, aZ - 1)) && (isMagneticCoil(aX + 7, aY - 3, aZ - 1))
    			&& (isMagneticCoil(aX + 6, aY + 4, aZ + 1)) && (isMagneticCoil(aX + 6, aY - 4, aZ + 1)) && (isMagneticCoil(aX + 6, aY + 4, aZ - 1)) && (isMagneticCoil(aX + 6, aY - 4, aZ - 1))
    			&& (isMagneticCoil(aX + 5, aY + 5, aZ + 1)) && (isMagneticCoil(aX + 5, aY - 5, aZ + 1)) && (isMagneticCoil(aX + 5, aY + 5, aZ - 1)) && (isMagneticCoil(aX + 5, aY - 5, aZ - 1))
    			&& (isMagneticCoil(aX + 4, aY + 6, aZ + 1)) && (isMagneticCoil(aX + 4, aY - 6, aZ + 1)) && (isMagneticCoil(aX + 4, aY + 6, aZ - 1)) && (isMagneticCoil(aX + 4, aY - 6, aZ - 1))
    			&& (isMagneticCoil(aX + 3, aY + 7, aZ + 1)) && (isMagneticCoil(aX + 3, aY - 7, aZ + 1)) && (isMagneticCoil(aX + 3, aY + 7, aZ - 1)) && (isMagneticCoil(aX + 3, aY - 7, aZ - 1))
    			&& (isMagneticCoil(aX + 2, aY + 7, aZ + 1)) && (isMagneticCoil(aX + 2, aY - 7, aZ + 1)) && (isMagneticCoil(aX + 2, aY + 7, aZ - 1)) && (isMagneticCoil(aX + 2, aY - 7, aZ - 1))
    			&& (isMagneticCoil(aX + 1, aY + 7, aZ + 1)) && (isMagneticCoil(aX + 1, aY - 7, aZ + 1)) && (isMagneticCoil(aX + 1, aY + 7, aZ - 1)) && (isMagneticCoil(aX + 1, aY - 7, aZ - 1))
    			&& (isMagneticCoil(aX, aY + 7, aZ + 1)) && (isMagneticCoil(aX, aY - 7, aZ + 1)) && (isMagneticCoil(aX, aY + 7, aZ - 1)) && (isMagneticCoil(aX, aY - 7, aZ - 1))
    			&& (isMagneticCoil(aX - 7, aY + 2, aZ + 1)) && (isMagneticCoil(aX - 7, aY - 2, aZ + 1)) && (isMagneticCoil(aX - 7, aY + 2, aZ - 1)) && (isMagneticCoil(aX - 7, aY - 2, aZ - 1))//Vertical
    			&& (isMagneticCoil(aX - 7, aY + 3, aZ + 1)) && (isMagneticCoil(aX - 7, aY - 3, aZ + 1)) && (isMagneticCoil(aX - 7, aY + 3, aZ - 1)) && (isMagneticCoil(aX - 7, aY - 3, aZ - 1))
    			&& (isMagneticCoil(aX - 6, aY + 4, aZ + 1)) && (isMagneticCoil(aX - 6, aY - 4, aZ + 1)) && (isMagneticCoil(aX - 6, aY + 4, aZ - 1)) && (isMagneticCoil(aX - 6, aY - 4, aZ - 1))
    			&& (isMagneticCoil(aX - 5, aY + 5, aZ + 1)) && (isMagneticCoil(aX - 5, aY - 5, aZ + 1)) && (isMagneticCoil(aX - 5, aY + 5, aZ - 1)) && (isMagneticCoil(aX - 5, aY - 5, aZ - 1))
    			&& (isMagneticCoil(aX - 4, aY + 6, aZ + 1)) && (isMagneticCoil(aX - 4, aY - 6, aZ + 1)) && (isMagneticCoil(aX - 4, aY + 6, aZ - 1)) && (isMagneticCoil(aX - 4, aY - 6, aZ - 1))
    			&& (isMagneticCoil(aX - 3, aY + 7, aZ + 1)) && (isMagneticCoil(aX - 3, aY - 7, aZ + 1)) && (isMagneticCoil(aX - 3, aY + 7, aZ - 1)) && (isMagneticCoil(aX - 3, aY - 7, aZ - 1))
    			&& (isMagneticCoil(aX - 2, aY + 7, aZ + 1)) && (isMagneticCoil(aX - 2, aY - 7, aZ + 1)) && (isMagneticCoil(aX - 2, aY + 7, aZ - 1)) && (isMagneticCoil(aX - 2, aY - 7, aZ - 1))
    			&& (isMagneticCoil(aX - 1, aY + 7, aZ + 1)) && (isMagneticCoil(aX - 1, aY - 7, aZ + 1)) && (isMagneticCoil(aX - 1, aY + 7, aZ - 1)) && (isMagneticCoil(aX - 1, aY - 7, aZ - 1)))
    			|| ((isMagneticCoil(aX + 1, aY + 2, aZ + 7)) && (isMagneticCoil(aX + 1, aY - 2, aZ + 7)) && (isMagneticCoil(aX - 1, aY + 2, aZ + 7)) && (isMagneticCoil(aX - 1, aY - 2, aZ + 7))//Vertical
    	    	&& (isMagneticCoil(aX + 1, aY + 3, aZ + 7)) && (isMagneticCoil(aX + 1, aY - 3, aZ + 7)) && (isMagneticCoil(aX - 1, aY + 3, aZ + 7)) && (isMagneticCoil(aX - 1, aY - 3, aZ + 7))
    	    	&& (isMagneticCoil(aX + 1, aY + 4, aZ + 6)) && (isMagneticCoil(aX + 1, aY - 4, aZ + 6)) && (isMagneticCoil(aX - 1, aY + 4, aZ + 6)) && (isMagneticCoil(aX - 1, aY - 4, aZ + 6))
    	    	&& (isMagneticCoil(aX + 1, aY + 5, aZ + 5)) && (isMagneticCoil(aX + 1, aY - 5, aZ + 5)) && (isMagneticCoil(aX - 1, aY + 5, aZ + 5)) && (isMagneticCoil(aX - 1, aY - 5, aZ + 5))
    	    	&& (isMagneticCoil(aX + 1, aY + 6, aZ + 4)) && (isMagneticCoil(aX + 1, aY - 6, aZ + 4)) && (isMagneticCoil(aX - 1, aY + 6, aZ + 4)) && (isMagneticCoil(aX - 1, aY - 6, aZ + 4))
    	    	&& (isMagneticCoil(aX + 1, aY + 7, aZ + 3)) && (isMagneticCoil(aX + 1, aY - 7, aZ + 3)) && (isMagneticCoil(aX - 1, aY + 7, aZ + 3)) && (isMagneticCoil(aX - 1, aY - 7, aZ + 3))
    	    	&& (isMagneticCoil(aX + 1, aY + 7, aZ + 2)) && (isMagneticCoil(aX + 1, aY - 7, aZ + 2)) && (isMagneticCoil(aX - 1, aY + 7, aZ + 2)) && (isMagneticCoil(aX - 1, aY - 7, aZ + 2))
    	    	&& (isMagneticCoil(aX + 1, aY + 7, aZ + 1)) && (isMagneticCoil(aX + 1, aY - 7, aZ + 1)) && (isMagneticCoil(aX - 1, aY + 7, aZ + 1)) && (isMagneticCoil(aX - 1, aY - 7, aZ + 1))
    	    	&& (isMagneticCoil(aX + 1, aY + 7, aZ)) && (isMagneticCoil(aX + 1, aY - 7, aZ)) && (isMagneticCoil(aX - 1, aY + 7, aZ)) && (isMagneticCoil(aX - 1, aY - 7, aZ))
    	    	&& (isMagneticCoil(aX + 1, aY + 2, aZ - 7)) && (isMagneticCoil(aX + 1, aY - 2, aZ - 7)) && (isMagneticCoil(aX - 1, aY + 2, aZ - 7)) && (isMagneticCoil(aX - 1, aY - 2, aZ - 7))//Vertical
    	    	&& (isMagneticCoil(aX + 1, aY + 3, aZ - 7)) && (isMagneticCoil(aX + 1, aY - 3, aZ - 7)) && (isMagneticCoil(aX - 1, aY + 3, aZ - 7)) && (isMagneticCoil(aX - 1, aY - 3, aZ - 7))
    	    	&& (isMagneticCoil(aX + 1, aY + 4, aZ - 6)) && (isMagneticCoil(aX + 1, aY - 4, aZ - 6)) && (isMagneticCoil(aX - 1, aY + 4, aZ - 6)) && (isMagneticCoil(aX - 1, aY - 4, aZ - 6))
    	    	&& (isMagneticCoil(aX + 1, aY + 5, aZ - 5)) && (isMagneticCoil(aX + 1, aY - 5, aZ - 5)) && (isMagneticCoil(aX - 1, aY + 5, aZ - 5)) && (isMagneticCoil(aX - 1, aY - 5, aZ - 5))
    	    	&& (isMagneticCoil(aX + 1, aY + 6, aZ - 4)) && (isMagneticCoil(aX + 1, aY - 6, aZ - 4)) && (isMagneticCoil(aX - 1, aY + 6, aZ - 4)) && (isMagneticCoil(aX - 1, aY - 6, aZ - 4))
    	    	&& (isMagneticCoil(aX + 1, aY + 7, aZ - 3)) && (isMagneticCoil(aX + 1, aY - 7, aZ - 3)) && (isMagneticCoil(aX - 1, aY + 7, aZ - 3)) && (isMagneticCoil(aX - 1, aY - 7, aZ - 3))
    	    	&& (isMagneticCoil(aX + 1, aY + 7, aZ - 2)) && (isMagneticCoil(aX + 1, aY - 7, aZ - 2)) && (isMagneticCoil(aX - 1, aY + 7, aZ - 2)) && (isMagneticCoil(aX - 1, aY - 7, aZ - 2))
    	    	&& (isMagneticCoil(aX + 1, aY + 7, aZ - 1)) && (isMagneticCoil(aX + 1, aY - 7, aZ - 1)) && (isMagneticCoil(aX - 1, aY + 7, aZ - 1)) && (isMagneticCoil(aX - 1, aY - 7, aZ - 1))));
    }
    
    //Check core casing
    private boolean checkCoreCasings(int aX, int aY, int aZ) {
    	return (isCoreMachineCasing(aX + 2, aY, aZ)) && (isCoreMachineCasing(aX + 2, aY, aZ + 1)) && (isCoreMachineCasing(aX + 2, aY, aZ - 1))//Central layer
    			&& (isCoreMachineCasing(aX - 2, aY, aZ)) && (isCoreMachineCasing(aX - 2, aY, aZ + 1)) && (isCoreMachineCasing(aX - 2, aY, aZ - 1))
    			&&(isCoreMachineCasing(aX, aY, aZ + 2)) && (isCoreMachineCasing(aX + 1, aY, aZ + 2)) && (isCoreMachineCasing(aX - 1, aY, aZ + 2))
    			&&(isCoreMachineCasing(aX, aY, aZ - 2)) && (isCoreMachineCasing(aX + 1, aY, aZ - 2)) && (isCoreMachineCasing(aX - 1, aY, aZ - 2))
    			&&(isCoreMachineCasing(aX + 1, aY, aZ + 1)) && (isCoreMachineCasing(aX + 1, aY, aZ - 1)) && (isCoreMachineCasing(aX - 1, aY, aZ + 1)) && (isCoreMachineCasing(aX - 1, aY, aZ - 1))
    			&&(isCoreMachineCasing(aX + 2, aY + 1, aZ)) && (isCoreMachineCasing(aX + 1, aY + 1, aZ)) && (isCoreMachineCasing(aX + 1, aY + 1, aZ + 1)) && (isCoreMachineCasing(aX + 1, aY + 1, aZ - 1))//Upper layer
    			&&(isCoreMachineCasing(aX, aY + 1, aZ + 2)) && (isCoreMachineCasing(aX, aY + 1, aZ + 1)) && (isCoreMachineCasing(aX, aY + 1, aZ - 1)) && (isCoreMachineCasing(aX, aY + 1, aZ - 2))
    			&&(isCoreMachineCasing(aX - 2, aY + 1, aZ)) && (isCoreMachineCasing(aX - 1, aY + 1, aZ)) && (isCoreMachineCasing(aX - 1, aY + 1, aZ + 1)) && (isCoreMachineCasing(aX - 1, aY + 1, aZ - 1))
    			&&(isCoreMachineCasing(aX + 2, aY - 1, aZ)) && (isCoreMachineCasing(aX + 1, aY - 1, aZ)) && (isCoreMachineCasing(aX + 1, aY - 1, aZ + 1)) && (isCoreMachineCasing(aX + 1, aY - 1, aZ - 1))//Lower layer
    			&&(isCoreMachineCasing(aX, aY - 1, aZ + 2)) && (isCoreMachineCasing(aX, aY - 1, aZ + 1)) && (isCoreMachineCasing(aX, aY - 1, aZ - 1)) && (isCoreMachineCasing(aX, aY - 1, aZ - 2))
    			&&(isCoreMachineCasing(aX - 2, aY - 1, aZ)) && (isCoreMachineCasing(aX - 1, aY - 1, aZ)) && (isCoreMachineCasing(aX - 1, aY - 1, aZ + 1)) && (isCoreMachineCasing(aX - 1, aY - 1, aZ - 1));
    }
    
    //Check loading chamber
    private boolean checkLoadingChamber(int aX, int aY, int aZ) {
    	return (isLoadingChamberCasing(aX + 1, aY, aZ)) && (isLoadingChamberCasing(aX - 1, aY, aZ)) 
    			&& (isLoadingChamberCasing(aX, aY, aZ + 1)) && (isLoadingChamberCasing(aX, aY, aZ - 1))
    			&& (isLoadingChamberCasing(aX, aY + 1, aZ)) && (isLoadingChamberCasing(aX, aY - 1, aZ));
    }
    
    //Check intermix chamber
    private boolean checkIntermixChamber(int aX, int aY, int aZ) {
    	if (xDir == 1 || xDir == -1) {
    		return (isIntermixChamberCasing(aX, aY + 2, aZ)) && (isIntermixChamberCasing(aX + 1, aY + 2, aZ)) && (isIntermixChamberCasing(aX - 1, aY + 2, aZ))
        			&&(isIntermixChamberCasing(aX, aY - 2, aZ)) && (isIntermixChamberCasing(aX + 1, aY - 2, aZ)) && (isIntermixChamberCasing(aX - 1, aY - 2, aZ));
    	}
    	return (isIntermixChamberCasing(aX, aY + 2, aZ)) && (isIntermixChamberCasing(aX, aY + 2, aZ + 1)) && (isIntermixChamberCasing(aX, aY + 2, aZ - 1))
    			&&(isIntermixChamberCasing(aX, aY - 2, aZ)) && (isIntermixChamberCasing(aX, aY - 2, aZ + 1)) && (isIntermixChamberCasing(aX, aY - 2, aZ - 1));
    }

    //Check Dynamo Hatch and Casing on his side
    private boolean addIfEnergyExtractor(int aX, int aY, int aZ, IGregTechTileEntity aTileEntity) {
    	
    	if(xDir == 1 || xDir == -1) {
    		return (addDynamoToMachineList(aTileEntity.getIGregTechTileEntity(aX, aY, aZ), 44))
            		&& isAdvancedMachineCasing(aX, aY, aZ - 1) && isAdvancedMachineCasing(aX, aY, aZ + 1);
    	}
        return (addDynamoToMachineList(aTileEntity.getIGregTechTileEntity(aX, aY, aZ), 44))
        		&& isAdvancedMachineCasing(aX - 1, aY, aZ) && isAdvancedMachineCasing(aX + 1, aY, aZ);
    }
    
    private boolean addIfFluidIO(int aX, int aY, int aZ, IGregTechTileEntity aTileEntity) {
    	if (xDir == 1 || xDir == -1) {
    		return ((addInputToMachineList(aTileEntity.getIGregTechTileEntity(aX, aY + 2, aZ - 1), 31)) && (addOutputToMachineList(aTileEntity.getIGregTechTileEntity(aX, aY + 2, aZ + 1), 31))
    				&& (addInputToMachineList(aTileEntity.getIGregTechTileEntity(aX, aY - 2, aZ - 1), 31)) && (addOutputToMachineList(aTileEntity.getIGregTechTileEntity(aX, aY - 2, aZ + 1), 31)))
    				|| (((addInputToMachineList(aTileEntity.getIGregTechTileEntity(aX, aY + 2, aZ + 1), 31)) && (addOutputToMachineList(aTileEntity.getIGregTechTileEntity(aX, aY + 2, aZ - 1), 31))
    				&& (addInputToMachineList(aTileEntity.getIGregTechTileEntity(aX, aY - 2, aZ + 1), 31)) && (addOutputToMachineList(aTileEntity.getIGregTechTileEntity(aX, aY - 2, aZ - 1), 31))));
    	}
        return ((addInputToMachineList(aTileEntity.getIGregTechTileEntity(aX - 1, aY + 2, aZ), 31)) && (addOutputToMachineList(aTileEntity.getIGregTechTileEntity(aX + 1, aY + 2, aZ), 31))
				&& (addInputToMachineList(aTileEntity.getIGregTechTileEntity(aX - 1, aY - 2, aZ), 31)) && (addOutputToMachineList(aTileEntity.getIGregTechTileEntity(aX + 1, aY - 2, aZ), 31)))
				|| (((addInputToMachineList(aTileEntity.getIGregTechTileEntity(aX + 1, aY + 2, aZ), 31)) && (addOutputToMachineList(aTileEntity.getIGregTechTileEntity(aX - 1, aY + 2, aZ), 31))
				&& (addInputToMachineList(aTileEntity.getIGregTechTileEntity(aX + 1, aY - 2, aZ), 31)) && (addOutputToMachineList(aTileEntity.getIGregTechTileEntity(aX - 1, aY - 2, aZ), 31))));
    }
    
    //Check needed block for Ring
    private boolean isAdvancedMachineCasing(int aX, int aY, int aZ) {
        return (getBaseMetaTileEntity().getBlock(aX, aY, aZ) == GregTech_API.sBlockCasings3) && (getBaseMetaTileEntity().getMetaID(aX, aY, aZ) == 12);
    }
    
  //Check needed Kinetic Coil
    private boolean isKineticCoil(int aX, int aY, int aZ) {
        return (getBaseMetaTileEntity().getBlock(aX, aY, aZ) == GregTech_API.sBlockCasings4) && (getBaseMetaTileEntity().getMetaID(aX, aY, aZ) == 7);
    }
    
  //Check needed Core coil
    private boolean isMagneticCoil(int aX, int aY, int aZ) {
        return (getBaseMetaTileEntity().getBlock(aX, aY, aZ) == GregTech_API.sBlockCasings5) && (getBaseMetaTileEntity().getMetaID(aX, aY, aZ) == 6);
    }
    
    //Check needed Core Casing
    private boolean isCoreMachineCasing(int aX, int aY, int aZ) {
        return (getBaseMetaTileEntity().getBlock(aX, aY, aZ) == GregTech_API.sBlockCasings4) && (getBaseMetaTileEntity().getMetaID(aX, aY, aZ) == 6);
    }
    
    //Check needed Core coil
    private boolean isLoadingChamberCasing(int aX, int aY, int aZ) {
        return (getBaseMetaTileEntity().getBlock(aX, aY, aZ) == GregTech_API.sBlockCasings3) && (getBaseMetaTileEntity().getMetaID(aX, aY, aZ) == 10);
    }
    
    //Check Core Block
    private boolean isIntermixChamberCasing(int aX, int aY, int aZ) {
        return (getBaseMetaTileEntity().getBlock(aX, aY, aZ) == GregTech_API.sBlockCasings2) && (getBaseMetaTileEntity().getMetaID(aX, aY, aZ) == 15);
    }
    
    /*Use for update machine ???
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
    	
    }*/
    
    /*Change generating logic
    @Override
    public boolean onRunningTick(ItemStack aStack) {
    	
    }*/
    
    /*Change Texture if machine active
    public boolean turnCasingActive(boolean status) {
        if (this.mEnergyHatches != null) {
            for (GT_MetaTileEntity_Hatch_Energy hatch : this.mEnergyHatches) {
                hatch.mMachineBlock = status ? (byte) 52 : (byte) 53;
            }
        }
        if (this.mOutputHatches != null) {
            for (GT_MetaTileEntity_Hatch_Output hatch : this.mOutputHatches) {
                hatch.mMachineBlock = status ? (byte) 52 : (byte) 53;
            }
        }
        if (this.mInputHatches != null) {
            for (GT_MetaTileEntity_Hatch_Input hatch : this.mInputHatches) {
                hatch.mMachineBlock = status ? (byte) 52 : (byte) 53;
            }
        }
        return true;
    }*/
    
    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_AntimatterReactor(this.mName);
    }
    
    //Damage for item in GUI
    @Override
    public int getDamageToComponent(ItemStack aStack) {
        return 0;
    }
    
    @Override
    public int getMaxEfficiency(ItemStack aStack) {
        return 10000;
    }
    
    @Override
    public int getAmountOfOutputs() {
        return 0;
    }
    
    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
    }
    
    //Zagraznenie. Not Use
    @Override
    public int getPollutionPerTick(ItemStack aStack) {
        return 0;
    }
    
    @Override
    public boolean explodesOnComponentBreak(ItemStack aStack) {
        return false;
    }
    
    //CHANGE!!! Info for netCard? @.@
    public String[] getInfoData() {
        return new String[]{
            "Diesel Engine",
            "Current Output: "+mEUt+" EU/t",
            "Fuel Consumption: "+fuelConsumption+"L/t",
            "Fuel Value: "+fuelValue+" EU/L",
            "Fuel Remaining: "+fuelRemaining+" Litres",
            "Current Efficiency: "+(mEfficiency/100)+"%"};
    }
    
    @Override
    public boolean isGivingInformation() {
        return true;
    }
}