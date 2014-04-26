/*
 *  Copyright (c) 2014, Lukas Tenbrink.
 *  * http://lukas.axxim.net
 */

package net.ivorius.psychedelicraft.client.rendering.effectWrappers;

import net.ivorius.psychedelicraft.Psychedelicraft;
import net.ivorius.psychedelicraft.client.rendering.shaders.DrugShaderHelper;
import net.ivorius.psychedelicraft.client.rendering.shaders.ShaderDigital;
import net.ivorius.psychedelicraft.entities.DrugHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

/**
 * Created by lukas on 26.04.14.
 */
public class WrapperDigitalMD extends ShaderWrapper<ShaderDigital>
{
    public ResourceLocation digitalTextTexture;

    public WrapperDigitalMD(String utils)
    {
        super(new ShaderDigital(Psychedelicraft.logger), getRL("shaderBasic.vert"), getRL("shaderDigital.frag"), utils);

        digitalTextTexture = new ResourceLocation(Psychedelicraft.MODID, Psychedelicraft.filePathTextures + "digitalText.png");
    }

    @Override
    public void setShaderValues(float partialTicks, int ticks)
    {
        DrugHelper drugHelper = DrugHelper.getDrugHelper(Minecraft.getMinecraft().renderViewEntity);

        if (drugHelper != null)
        {
            shaderInstance.digital = drugHelper.getDrugValue("Zero");
            shaderInstance.maxDownscale = drugHelper.getDigitalEffectPixelResize();
            shaderInstance.digitalTextTexture = DrugShaderHelper.getTextureIndex(digitalTextTexture);
        }
        else
        {
            shaderInstance.digital = 0.0f;
        }
    }

    @Override
    public void update()
    {

    }

    @Override
    public boolean wantsDepthBuffer()
    {
        return false;
    }
}