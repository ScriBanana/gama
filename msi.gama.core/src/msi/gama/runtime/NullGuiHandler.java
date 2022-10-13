/*******************************************************************************************************
 *
 * HeadlessListener.java, in msi.gama.core, is part of the source code of the GAMA modeling and simulation platform
 * (v.1.8.2).
 *
 * (c) 2007-2022 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama for license information and contacts.
 *
 ********************************************************************************************************/
package msi.gama.runtime;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;

import msi.gama.common.interfaces.IConsoleDisplayer;
import msi.gama.common.interfaces.IDisplayCreator;
import msi.gama.common.interfaces.IDisplayCreator.DisplayDescription;
import msi.gama.common.interfaces.IDisplaySurface;
import msi.gama.common.interfaces.IGamaView;
import msi.gama.common.interfaces.IGamlLabelProvider;
import msi.gama.common.interfaces.IGui;
import msi.gama.common.interfaces.IKeyword;
import msi.gama.common.interfaces.IStatusDisplayer;
import msi.gama.kernel.experiment.IExperimentPlan;
import msi.gama.kernel.experiment.IParameter;
import msi.gama.kernel.experiment.ITopLevelAgent;
import msi.gama.kernel.model.IModel;
import msi.gama.kernel.simulation.SimulationAgent;
import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.shape.GamaPoint;
import msi.gama.metamodel.shape.IShape;
import msi.gama.outputs.IDisplayOutput;
import msi.gama.outputs.LayeredDisplayOutput;
import msi.gama.outputs.display.NullDisplaySurface;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gama.util.GamaColor;
import msi.gama.util.GamaFont;
import msi.gama.util.GamaMapFactory;
import msi.gama.util.IList;
import msi.gama.util.IMap;
import msi.gama.util.file.IFileMetaDataProvider;
import msi.gama.util.file.IGamaFileMetaData;
import msi.gaml.architecture.user.UserPanelStatement;
import msi.gaml.compilation.ast.ISyntacticElement;
import msi.gaml.descriptions.ActionDescription;
import msi.gaml.statements.test.CompoundSummary;
import msi.gaml.statements.test.TestExperimentSummary;
import ummisco.gama.dev.utils.DEBUG;

/**
 * The listener interface for receiving headless events. The class that is interested in processing a headless event
 * implements this interface, and the object created with that class is registered with a component using the
 * component's <code>addHeadlessListener<code> method. When the headless event occurs, that object's appropriate method
 * is invoked.
 *
 * @see HeadlessEvent
 */
public class NullGuiHandler implements IGui {

	// See #2996: simplification of the logging done in this class
	// static Logger LOGGER = LogManager.getLogManager().getLogger("");
	// static Level LEVEL = Level.ALL;
	// final ThreadLocal<BufferedWriter> outputWriter = new ThreadLocal<>();

	static {
		// See #2996: simplification of the logging done in this class
		// if (GAMA.isInHeadLessMode()) {

		//
		// for (final Handler h : LOGGER.getHandlers()) {
		// h.setLevel(Level.ALL);
		// }
		// LOGGER.setLevel(Level.ALL);
		// }
		GAMA.setHeadlessGui(new NullGuiHandler());
	}

	@Override
	public Map<String, Object> openUserInputDialog(final IScope scope, final String title,
			final List<IParameter> parameters, final GamaFont font, final GamaColor color, final Boolean showTitle) {
		final Map<String, Object> initialValues = GamaMapFactory.create();
		parameters.forEach(p -> { initialValues.put(p.getName(), p.getInitialValue(scope)); });
		return initialValues;
	}

	@Override
	public IMap<String, IMap<String, Object>> openWizard(final IScope scope, final String title,
			final ActionDescription finish, final IList<IMap<String, Object>> pages) {
		final IMap<String, IMap<String, Object>> initialValues = GamaMapFactory.create();
		for (IMap l : pages) {
			final IMap<String, Object> initialValuesPage = GamaMapFactory.create();
			String t = (String) l.get(IKeyword.TITLE);

			initialValues.put(t, initialValuesPage);
			@SuppressWarnings ("unchecked") IList<IParameter> ps = (IList<IParameter>) l.get(IKeyword.PARAMETERS);
			if (ps != null) { ps.forEach(p -> { initialValuesPage.put(p.getName(), p.getInitialValue(scope)); }); }

		}

		return initialValues;
	}

	@Override
	public Boolean openUserInputDialogConfirm(final IScope scope, final String title, final String message) {
		return true;
	}

	// See #2996: simplification of the logging done in this class
	// public void registerJob(final BufferedWriter w) {
	// this.outputWriter.set(w);
	// }
	//
	// public BufferedWriter leaveJob() {
	// final BufferedWriter res = this.outputWriter.get();
	// this.outputWriter.remove();
	// return res;
	// }

	@Override
	public boolean copyToClipboard(final String text) {
		return false;
	}

	@Override
	public void openUserControlPanel(final IScope scope, final UserPanelStatement panel) {}

	@Override
	public void closeDialogs(final IScope scope) {}

	@Override
	public IAgent getHighlightedAgent() { return null; }

	@Override
	public void setHighlightedAgent(final IAgent a) {}

	@Override
	public IGamaView showView(final IScope scope, final String viewId, final String name, final int code) {
		return null;
	}

	@Override
	public void openMessageDialog(final IScope scope, final String message) {
		logger.log("Message: " + message);
	}

	@Override
	public void openErrorDialog(final IScope scope, final String error) {
		logger.log("Error: " + error);
	}

	@Override
	public void showParameterView(final IScope scope, final IExperimentPlan exp) {}

	@Override
	public void runtimeError(final IScope scope, final GamaRuntimeException g) {
		logger.log("Runtime error: " + g.getMessage());
	}

	@Override
	public boolean confirmClose(final IExperimentPlan experiment) {
		return true;
	}

	@Override
	public void prepareForExperiment(final IScope scope, final IExperimentPlan exp) {}

	@Override
	public boolean openSimulationPerspective(final IModel model, final String id) {
		return true;
	}

	// @SuppressWarnings ("rawtypes") static Map<String, Class> displayClasses = null;

	@Override
	public IDisplaySurface createDisplaySurfaceFor(final LayeredDisplayOutput output, final Object... objects) {

		IDisplaySurface surface = null;
		final IDisplayCreator creator = DISPLAYS.get("image");
		if (creator == null) return new NullDisplaySurface();
		surface = creator.create(output);
		surface.outputReloaded();
		return surface;
	}

	@Override
	public void editModel(final IScope scope, final Object eObject) {}

	@Override
	public void updateParameterView(final IScope scope, final IExperimentPlan exp) {}

	@Override
	public void setSelectedAgent(final IAgent a) {}

	@Override
	public void cleanAfterExperiment() {
		// DEBUG.LOG("[Headless] Clean after experiment.");
		// try {
		// outputWriter.get().flush();
		// outputWriter.get().close();
		// } catch (final IOException e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public void runModel(final Object object, final String exp) {}

	/**
	 * Method updateSpeedDisplay()
	 *
	 * @see msi.gama.common.interfaces.IGui#updateSpeedDisplay(java.lang.Double)
	 */
	@Override
	public void updateSpeedDisplay(final IScope scope, final Double d, final boolean notify) {}

	/**
	 * Method getMetaDataProvider()
	 *
	 * @see msi.gama.common.interfaces.IGui#getMetaDataProvider()
	 */
	@Override
	public IFileMetaDataProvider getMetaDataProvider() {
		return new IFileMetaDataProvider() {

			@Override
			public void storeMetaData(final IResource file, final IGamaFileMetaData data, final boolean immediately) {}

			@Override
			public IGamaFileMetaData getMetaData(final Object element, final boolean includeOutdated,
					final boolean immediately) {
				return new IGamaFileMetaData() {

					@Override
					public boolean hasFailed() {
						return false;
					}

					@Override
					public String toPropertyString() {
						return "";
					}

					@Override
					public void setModificationStamp(final long modificationStamp) {}

					@Override
					public Object getThumbnail() { return ""; }

					@Override
					public String getSuffix() { return ""; }

					@Override
					public void appendSuffix(final StringBuilder sb) {}

					@Override
					public long getModificationStamp() { return 0; }

					@Override
					public String getDocumentation() { return ""; }
				};
			}

		};
	}

	/**
	 * Method closeSimulationViews()
	 *
	 * @see msi.gama.common.interfaces.IGui#closeSimulationViews(boolean)
	 */
	@Override
	public void closeSimulationViews(final IScope scope, final boolean andOpenModelingPerspective,
			final boolean immediately) {}

	/**
	 * Method getDisplayDescriptionFor()
	 *
	 * @see msi.gama.common.interfaces.IGui#getDisplayDescriptionFor(java.lang.String)
	 */
	@Override
	public DisplayDescription getDisplayDescriptionFor(final String name) {
		return new DisplayDescription(null, "display", "msi.gama.core");
	}

	/**
	 * Method getFrontmostSimulationState()
	 *
	 * @see msi.gama.common.interfaces.IGui#getExperimentState()
	 */
	@Override
	public String getExperimentState(final String uid) {
		return RUNNING; // ???
	}

	/**
	 * Method updateSimulationState()
	 *
	 * @see msi.gama.common.interfaces.IGui#updateExperimentState(java.lang.String)
	 */
	@Override
	public void updateExperimentState(final IScope scope, final String state) {}

	/**
	 * Method updateSimulationState()
	 *
	 * @see msi.gama.common.interfaces.IGui#updateExperimentState()
	 */
	@Override
	public void updateExperimentState(final IScope scope) {}

	@Override
	public void updateViewTitle(final IDisplayOutput output, final SimulationAgent agent) {}

	@Override
	public void openWelcomePage(final boolean b) {}

	@Override
	public void updateDecorator(final String string) {}

	/** The status. */
	protected IStatusDisplayer status = null;


	/** The console. */
	protected IConsoleDisplayer console = null;

	/** The logger. */
	private IHeadlessLogger logger = DEBUG::LOG;

	/**
	 * The Interface IHeadlessLogger.
	 */
	public interface IHeadlessLogger {

		/**
		 * Log.
		 *
		 * @param message
		 *            the message
		 */
		void log(String message);
	}

	/**
	 * Sets the headless logger.
	 *
	 * @param logger
	 *            the new headless logger
	 */
	public void setHeadlessLogger(final IHeadlessLogger logger) { this.logger = logger; }

	@Override
	public IStatusDisplayer getStatus() { 
	
		if (status == null) {
			status = new IStatusDisplayer() {

				@Override
				public void resumeStatus(IScope scope) {}

				@Override
				public void waitStatus(final String string, IScope scope) {}

				@Override
				public void informStatus(final String string, IScope scope) {
				}

				@Override
				public void errorStatus(final String message, IScope scope) {
				}

				@Override
				public void setSubStatusCompletion(final double status, IScope scope) {}

				@Override
				public void setStatus(final String msg, final GamaColor color, IScope scope) {
				}

				@Override
				public void informStatus(final String message, final String icon, IScope scope) {
				}

				@Override
				public void setStatus(final String msg, final String icon, IScope scope) {
				}

				@Override
				public void beginSubStatus(final String name, IScope scope) {}

				@Override
				public void endSubStatus(final String name, IScope scope) {}

				@Override
				public void neutralStatus(final String string, IScope scope) {			
				}

			};
		}
		return status; 
	}

	
	@Override
	public IConsoleDisplayer getConsole() { 
	
		if (console == null) {
			console = new IConsoleDisplayer() {

				@Override
				public void debugConsole(final int cycle, final String s, final ITopLevelAgent root, final GamaColor color) {
				}

				@Override
				public void debugConsole(final int cycle, final String s, final ITopLevelAgent root) {
					informConsole(s, root);
				}

				@Override
				public void informConsole(final String s, final ITopLevelAgent root, final GamaColor color) {
					informConsole(s, root);
				}

				@Override
				public void informConsole(final String s, final ITopLevelAgent root) {
					logger.log(s);
				}

				@Override
				public void showConsoleView(final ITopLevelAgent agent) {}

				@Override
				public void eraseConsole(final boolean setToNull) {}

			};
		}
		return console; 
	}

	@Override
	public void clearErrors(final IScope scope) {}

	@Override
	public void run(final String taskName, final Runnable opener, final boolean asynchronous) {
		if (opener != null) {
			if (asynchronous) {
				new Thread(opener).start();
			} else {
				opener.run();
			}
		}
	}

	@Override
	public void setFocusOn(final IShape o) {}

	@Override
	public void applyLayout(final IScope scope, final Object layout) {}

	@Override
	public void displayErrors(final IScope scope, final List<GamaRuntimeException> list) {}

	@Override
	public GamaPoint getMouseLocationInModel() { return new GamaPoint(0, 0); }

	@Override
	public void setMouseLocationInModel(final GamaPoint modelCoordinates) {}

	@Override
	public IGamlLabelProvider getGamlLabelProvider() {
		return new IGamlLabelProvider() {

			@Override
			public String getText(final ISyntacticElement element) {
				return "";
			}

			@Override
			public Object getImage(final ISyntacticElement element) {
				return null;
			}
		};
	}

	@Override
	public void exit() {
		System.exit(0);
	}

	@Override
	public IGamaView.Test openTestView(final IScope scope, final boolean remainOpen) {
		// final String pathToFile = scope.getModel().getFilePath().replace(scope.getModel().getWorkingPath(), "");
		// log("----------------------------------------------------------------");
		// log(" Running tests declared in " + pathToFile);
		// log("----------------------------------------------------------------");
		return null;
	}

	@Override
	public void displayTestsResults(final IScope scope, final CompoundSummary<?, ?> summary) {
		logger.log(summary.toString());
	}

	@Override
	public List<TestExperimentSummary> runHeadlessTests(final Object model) {
		return null;
	}

	@Override
	public void endTestDisplay() {}

	@Override
	public void refreshNavigator() {}

	@Override
	public boolean isInDisplayThread() { return false; }

	@Override
	public Iterable<IDisplaySurface> getAllDisplaySurfaces() { return Collections.EMPTY_LIST; }

	@Override
	public IDisplaySurface getFrontmostDisplaySurface() { return null; }

}