package com.lmph.be.utility;

import com.lmph.be.controller.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ControllerUtil
{
    /**
     * Show a page dedicated to error code 500: Internal Server Error. The Back to Recent Page will have the link
     * specified by the <code>returnUrl</code>
     *
     * @author Jeffrey John Javison
     * @since 13-Dec-2023
     * @param redirectAttributes the {@link RedirectAttributes} of the current mapping
     * @param message the message to show
     * @param returnUrl the desired URL to redirect to
     * @return the URL for the {@link ErrorController#error500(Model)}
     */
    public static String showInternalErrorMessage(RedirectAttributes redirectAttributes, String message, String returnUrl){
        redirectAttributes.addFlashAttribute("errorMessage", message);
        redirectAttributes.addFlashAttribute("recentUrl", returnUrl);
        return "redirect:/error/500";
    }


    /**
     * Show a page dedicated to error code 500: Internal Server Error.
     *
     * @author Jeffrey John Javison
     * @since 13-Dec-2023
     * @param redirectAttributes the {@link RedirectAttributes} of the current mapping
     * @param message the message to show
     * @return the URL for the {@link ErrorController#error500(Model)}
     */
    public static String showInternalErrorMessage(RedirectAttributes redirectAttributes, String message){
        redirectAttributes.addFlashAttribute("errorMessage", message);
        redirectAttributes.addFlashAttribute("recentUrl", "/home");
        return "redirect:/error/500";
    }
}
