package com.stefanini.hackathon2.exceptions;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
	private static final Logger log = Logger.getLogger(CustomExceptionHandler.class.getCanonicalName());
    private ExceptionHandler wrapped;
 
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }
 
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
 
    @Override
    public void handle() throws FacesException {
 
        final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            
            // pega a exception do contexto
            Throwable throwable = context.getException();
 
            final FacesContext facesContext = FacesContext.getCurrentInstance();
            final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
            final NavigationHandler navigatonHandler = facesContext.getApplication().getNavigationHandler();
 
            //aqui é possivel fazer o que quiser coma exception
            try {
                //log error ?
                log.log(Level.SEVERE, "Critical Exception!", throwable);
 
                //Redirecionar para página de error
                requestMap.put("exceptionMessage", throwable.getMessage());
                navigatonHandler.handleNavigation(facesContext, null, "/error");
                facesContext.renderResponse();
 
                // remover o comentário abaixo de quiser motrar o error usando uma Mensagem de erro do JSF
                //JsfUtil.addErrorMessage(t.getMessage());
 
            } finally {
                //remover da fila
                iterator.remove();
            }
        }
        //parent hanle
        getWrapped().handle();
    }
}
