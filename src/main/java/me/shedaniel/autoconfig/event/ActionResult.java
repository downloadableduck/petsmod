package me.shedaniel.autoconfig.event;

/**
 * 1.8.9 has no net.minecraft.util.EnumActionResult (it only gained PASS/SUCCESS/FAIL
 * in 1.9), and the 1.8.9 EnumAction only has SUCCESS/FAIL, so the three-state result
 * is modelled locally to keep the original control flow intact.
 */
public enum ActionResult {
    PASS,
    SUCCESS,
    FAIL
}