package ca.ualberta.cs.taggingapp.models;

/**
 * Stores the settings for the BoundingBox
 * 
 * @author wyatt
 *
 */
public enum BoundingBoxSetting {
	DRAG {

		@Override
		public String getName() {
			return "Drag";
		}

	},
	DOUBLE_TAP {
		@Override
		public String getName() {
			return "Double Tap";
		}
	};

	abstract public String getName();

	public static String[] getNames() {
		BoundingBoxSetting[] theSettings = BoundingBoxSetting.values();
		String[] theNames = new String[theSettings.length];

		for (int i = 0; i < theSettings.length; i++) {
			theNames[i] = theSettings[i].getName();
		}

		return theNames;
	}

	public static BoundingBoxSetting getFromIndex(int index) {
		BoundingBoxSetting[] theSettings = BoundingBoxSetting.values();

		return theSettings[index];
	}
}
