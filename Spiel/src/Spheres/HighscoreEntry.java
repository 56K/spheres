package Spheres;
public class HighscoreEntry implements Comparable<HighscoreEntry>
{
	private int points;
	private String user;
	
	public HighscoreEntry(int points, String user) {
		super();
		this.points = points;
		this.user = user;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public int compareTo(HighscoreEntry other) {
		return other.points-points;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HighscoreEntry) {
			HighscoreEntry e = (HighscoreEntry) obj;
			return e.points==points && e.user.equals(user);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if(user==null)
			return points;
		return user.hashCode()+points;
	}
}